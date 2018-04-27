package com.hym.wxtest.service.impl;

import com.hym.wxtest.entity.Area;
import com.hym.wxtest.service.AreaService;
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
public class AreaServiceImplTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void listArea() {
//        List<Area> areas = areaService.listArea();
//        assertEquals(1, areas.size());
    }

    @Test
    public void findAreaById() {
//        Area area = areaService.findAreaById(1);
//        assertEquals("北京南", area.getAreaName());
    }

    @Test
    public void saveArea() {
//        Area area = new Area();
//        area.setAreaName("上海");
//        area.setPriority(2);
//        area.setCreateTime(new Date());
//        area.setLastEditTime(new Date());
//        int influenceNum = areaService.saveArea(area);
//        assertEquals(1, influenceNum);
    }

    @Test
    public void updateArea() {
//        Area area = new Area();
//        area.setAreaId(2);
//        area.setAreaName("上海南");
//        area.setPriority(2);
//        area.setLastEditTime(new Date());
//        int influenceNum = areaService.updateArea(area);
//        assertEquals(1, influenceNum);
    }

    @Test
    public void deleteArea() {
//        int influenceNum = areaService.deleteArea(2);
//        assertEquals(1, influenceNum);
    }
}