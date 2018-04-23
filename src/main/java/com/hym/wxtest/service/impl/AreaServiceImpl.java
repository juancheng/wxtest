package com.hym.wxtest.service.impl;

import com.hym.wxtest.dao.AreaDao;
import com.hym.wxtest.entity.Area;
import com.hym.wxtest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> listArea() {
        return areaDao.queryArea();
    }

    @Override
    public Area findAreaById(Integer areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Override
    public int saveArea(Area area) {
        return areaDao.insertArea(area);
    }

    @Override
    public int updateArea(Area area) {
        return areaDao.updateArea(area);
    }

    @Override
    public int deleteArea(Integer areaId) {
        return areaDao.deleteArea(areaId);
    }
}
