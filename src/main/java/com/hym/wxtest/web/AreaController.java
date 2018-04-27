package com.hym.wxtest.web;

import com.hym.wxtest.dto.AreaResult;
import com.hym.wxtest.dto.BaseResult;
import com.hym.wxtest.entity.Area;
import com.hym.wxtest.exception.AreaException;
import com.hym.wxtest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/list")
    public BaseResult listArea() {
        return areaService.listArea();
    }

    @GetMapping("/{areaId}")
    public BaseResult findAreaById(@PathVariable("areaId") Integer areaId) {
        return areaService.findAreaById(areaId);
    }

    @PostMapping("/save")
    public BaseResult saveArea(@RequestBody Area area) {
        try {
           return areaService.saveArea(area);
        } catch (AreaException e) {
            return new AreaResult<>(e);
        }
    }

    @PutMapping("/update")
    public BaseResult updateArea(@RequestBody Area area) {
        try {
            return areaService.updateArea(area);
        } catch (AreaException e) {
            return new AreaResult<Area>(e);
        }
    }

    @DeleteMapping("/{areaId}")
    public BaseResult deleteArea(@PathVariable("areaId") Integer areaId) {
        try {
            return areaService.deleteArea(areaId);
        } catch (AreaException e) {
            return new AreaResult(e);
        }
    }
}
