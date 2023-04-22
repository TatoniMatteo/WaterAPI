package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.Data;
import com.tatonimatteo.waterapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("data/filter/station-date/{stationId}/{startDate}/{endDate}")
    public List<Data> getByStationIdAndDayBetween(
            @PathVariable Long stationId,
            @PathVariable Date startDate,
            @PathVariable Date endDate
    ) {
        return dataService.findByStationIdAndDayBetween(stationId, startDate, endDate);
    }

    @GetMapping("data/filter/sensor-date/{sensorId}/{startDate}/{endDate}")
    public List<Data> getBySensorIdAndDayBetween(
            @PathVariable Long sensorId,
            @PathVariable Date startDate,
            @PathVariable Date endDate
    ) {
        return dataService.findBySensorIdAndDayBetween(sensorId, startDate, endDate);
    }

    @GetMapping("data/filter/station/{stationId}")
    public List<Data> getByStationId(@PathVariable Long stationId) {
        return dataService.findByStationId(stationId);
    }


    @GetMapping("data/currentstate/{stationId}")
    public Integer getCurrentState(@PathVariable Long stationId) {
        return dataService.getCurrentState(stationId);
    }
}
