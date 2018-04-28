package com.hym.wxtest.service;

import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.entity.Area;
import com.hym.wxtest.exception.AreaException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 与Area相关的service接口
 */
public interface AreaService {
    BaseResult listArea();
    BaseResult findAreaById(Integer areaId);
    @Transactional
    BaseResult saveArea(Area area) throws AreaException;
    @Transactional
    BaseResult updateArea(Area area) throws AreaException;
    @Transactional
    BaseResult deleteArea(Integer areaId) throws AreaException;
    BaseResult uploadFiles(MultipartFile file) throws AreaException;
}
