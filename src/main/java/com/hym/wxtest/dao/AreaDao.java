package com.hym.wxtest.dao;

import com.hym.wxtest.entity.Area;

import java.util.List;

/**
 * 取消使用注解配置redis缓存策略
 * 因为 insert* 与 update* 存储返回的数据（Integer对象）
 * 但是 查询要获取Area对象
 */
// @CacheConfig(cacheNames = "area")
public interface AreaDao {
    List<Area> queryArea();
    // @Cacheable(key = "#p0")
    Area queryAreaById(Integer areaId);
    // @CachePut(key = "#p0.areaId")
    int insertArea(Area area);
    // @CachePut(key = "#p0.areaId")
    int updateArea(Area area);
    // @CacheEvict(key = "#p0")
    int deleteArea(Integer areaId);
}
