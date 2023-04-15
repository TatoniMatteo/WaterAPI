package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.Sensor;
import com.tatonimatteo.waterapi.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/sensor")
    public List<Sensor> getAll() {
        return sensorService.findAll();
    }

    @GetMapping("/sensor/filter/station/{stationId}")
    public List<Sensor> getByStation(@PathVariable Long stationId) {
        return sensorService.findByStationId(stationId);
    }

}

