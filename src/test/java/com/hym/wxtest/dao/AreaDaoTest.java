package com.hym.wxtest.dao;

import com.hym.wxtest.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void queryArea() {
        List<Area> areas = areaDao.queryArea();
        assertEquals(1, areas.size());
    }

    @Test
    public void queryAreaById() {
        Area area = areaDao.queryAreaById(1);
        assertEquals("北京", area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area = new Area();
        area.setAreaName("南京");
        area.setPriority(1);
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        int fluenceNum = areaDao.insertArea(area);
        assertEquals(1, fluenceNum);
    }

    @Test
    public void updateArea() {
        Area area = new Area();
        area.setAreaId(1);
        area.setAreaName("北京南");
        area.setPriority(2);
        area.setLastEditTime(new Date());
        int fluenceNum = areaDao.updateArea(area);
        assertEquals(1, fluenceNum);
    }

    @Test
    public void deleteArea() {
        int fluenceNum = areaDao.deleteArea(2);
        assertEquals(1, fluenceNum);
    }
}