package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.Sensor;
import com.tatonimatteo.waterapi.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @GetMapping("/all")
    public List<Sensor> getAll() {
        return sensorService.findAll();
    }

    @GetMapping("/filter/station/{stationId}")
    public List<Sensor> getByStation(@PathVariable Long stationId) {
        return sensorService.findByStationId(stationId);
    }

}

