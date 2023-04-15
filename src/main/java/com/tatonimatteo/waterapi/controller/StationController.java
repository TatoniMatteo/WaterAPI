package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.Station;
import com.tatonimatteo.waterapi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/stations")
    public List<Station> getAll() {
        return stationService.findAll();
    }
}
