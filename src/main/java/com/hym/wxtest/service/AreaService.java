package com.hym.wxtest.service;

import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.entity.Area;
import com.hym.wxtest.exception.AreaException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 与Area相关的service接口
 */
public interface AreaService {
    BaseResult<List<Area>> listArea();
    BaseResult<Area> findAreaById(Integer areaId);
    @Transactional
    BaseResult<Area> saveArea(Area area) throws AreaException;
    @Transactional
    BaseResult<Area> updateArea(Area area) throws AreaException;
    @Transactional
    BaseResult deleteArea(Integer areaId) throws AreaException;
    BaseResult uploadFiles(MultipartFile file) throws AreaException;
}
