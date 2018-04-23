package com.hym.wxtest.service;

import com.hym.wxtest.entity.Area;

import java.util.List;

/**
 * 与Area相关的service接口
 */
public interface AreaService {
    List<Area> listArea();
    Area findAreaById(Integer areaId);
    int saveArea(Area area);
    int updateArea(Area area);
    int deleteArea(Integer areaId);
}
