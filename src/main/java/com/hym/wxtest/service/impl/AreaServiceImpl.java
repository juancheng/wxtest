package com.hym.wxtest.service.impl;

import com.hym.wxtest.dao.AreaDao;
import com.hym.wxtest.dto.AreaResult;
import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.entity.Area;
import com.hym.wxtest.enums.AreaEnum;
import com.hym.wxtest.enums.ExpireTimeEnum;
import com.hym.wxtest.exception.AreaException;
import com.hym.wxtest.service.AreaService;
import com.hym.wxtest.utils.OssClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaDao areaDao;

    // RedisConfiguration类可以不存在，那么需要使用@Resource
    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate<String, Area> redisTemplate;

    @Override
    public BaseResult listArea() {
        List<Area> areas = areaDao.queryArea();
        if (areas.size() > 0) {
            return new AreaResult<>(AreaEnum.SUCCESS, areas);
        }else {
            return new AreaResult(AreaEnum.FIND_DATA_NONE);
        }
    }

    /**
     * 使用redis缓存： key_areaId
     * 缓存存在，返回数据
     * 缓存不存在，查询数据，保存缓存中
     * @param areaId
     * @return
     */
    @Override
    public BaseResult findAreaById(Integer areaId) {
        // 定义key
        String key =  "area_" + areaId;
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) { // 缓存存在
            Area area = redisTemplate.opsForValue().get(key);
            logger.info("form cache={}", area);
            return new AreaResult<>(AreaEnum.SUCCESS, area);
        }
        Area area = areaDao.queryAreaById(areaId);
        if (area != null) {
            // 保存缓存
            redisTemplate.opsForValue().set(key, area, ExpireTimeEnum.REDIS_SAVE_TIME.getTime(), ExpireTimeEnum.REDIS_SAVE_TIME.getTimeUnit());
            logger.info("save cache={}", area);
            return new AreaResult<>(AreaEnum.SUCCESS, area);
        } else {
            return new AreaResult(AreaEnum.MATCH_DATA_NONE);
        }
    }

    /**
     * 使用redis缓存： key_areaId
     * 存储到数据库中成功后，保存到缓存中
     * @param area
     * @return
     * @throws AreaException
     */
    @Override
    public BaseResult<Area> saveArea(Area area) throws AreaException {
        int influenceNum = areaDao.insertArea(area);
        if (influenceNum >= 1) {
            // 定义key
            String key =  "area_" + area.getAreaId();
            redisTemplate.opsForValue().set(key, area, ExpireTimeEnum.REDIS_SAVE_TIME.getTime(), ExpireTimeEnum.REDIS_SAVE_TIME.getTimeUnit());
            return new AreaResult<>(AreaEnum.SUCCESS_SAVE, area);
        }else {
            throw new AreaException(AreaEnum.FAIL_SAVE);
        }
    }

    /**
     * 使用redis缓存： key_areaId
     * 更新到数据库中成功后，保存或者更新缓存数据
     * @param area
     * @return
     * @throws AreaException
     */
    @Override
    public BaseResult updateArea(Area area) throws AreaException {
        int influenceNum = areaDao.updateArea(area);
        if (influenceNum >= 1) {
            // 定义key
            String key =  "area_" + area.getAreaId();
            redisTemplate.opsForValue().set(key, area, ExpireTimeEnum.REDIS_SAVE_TIME.getTime(), ExpireTimeEnum.REDIS_SAVE_TIME.getTimeUnit());
            return new AreaResult(AreaEnum.SUCCESS_UPDATE, area);
        } else {
            throw new AreaException(AreaEnum.FAIL_UPDATE);
        }
    }

    /**
     * 使用redis缓存： key_areaId
     * 从数据库中删除成功后，查询缓存中是否存在，存在删除缓存中的数据
     * 可能导致的问题：数据库中的数据删除成功，缓存中的数据删除失败，导致删除的数据依然可以查询到
     * @param areaId
     * @return
     * @throws AreaException
     */
    @Override
    public BaseResult deleteArea(Integer areaId) throws AreaException {
        // 先执行缓存的数据删除操作, 再执行数据库持久化数据的删除
        // 定义key
        String key =  "area_" + areaId;
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) { // 缓存存在保存或者更新数值
            redisTemplate.delete(key);
        }
        int influenceNum = areaDao.deleteArea(areaId);
        if (influenceNum >= 1) {
            return new AreaResult(AreaEnum.SUCCESS_DELETE);
        } else {
            throw new AreaException(AreaEnum.FAIL_DELETE);
        }
    }

    @Override
    public BaseResult uploadFiles(MultipartFile file) throws AreaException {
        try {
            String fileUrl = OssClientUtil.uploadFile(file);
            return new AreaResult(AreaEnum.SUCCESS, fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AreaException(AreaEnum.FIND_DATA_NONE);
        }
    }
}
