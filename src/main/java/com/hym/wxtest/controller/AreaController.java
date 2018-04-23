package com.hym.wxtest.controller;

import com.hym.wxtest.entity.Area;
import com.hym.wxtest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/list")
    public Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> areas = areaService.listArea();
        modelMap.put("data", areas);
        return modelMap;
    }

    @GetMapping("/{areaId}")
    public Map<String, Object> findAreaById(@PathVariable("areaId") Integer areaId) {
        Map<String, Object> modelMap = new HashMap<>();
        Area area = areaService.findAreaById(areaId);
        modelMap.put("data", area);
        return modelMap;
    }

    @PostMapping("/save")
    public Map<String, Object> saveArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<>();
        areaService.saveArea(area);
        modelMap.put("message", "save success");
        return modelMap;
    }

    @PutMapping("/update")
    public Map<String, Object> updateArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<>();
        areaService.updateArea(area);
        modelMap.put("message", "update success");
        return modelMap;
    }

    @DeleteMapping("/{areaId}")
    public Map<String, Object> deleteArea(@PathVariable("areaId") Integer areaId) {
        Map<String, Object> modelMap = new HashMap<>();
        areaService.deleteArea(areaId);
        modelMap.put("message", "delete success");
        return modelMap;
    }
}
