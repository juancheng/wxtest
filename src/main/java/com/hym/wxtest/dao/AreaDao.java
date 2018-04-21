package com.hym.wxtest.dao;

import com.hym.wxtest.entity.Area;

import java.util.List;

public interface AreaDao {
    List<Area> queryArea();
    Area queryAreaById(Integer areaId);
    int insertArea(Area area);
    int updateArea(Area area);
    int deleteArea(Integer areaId);
}
