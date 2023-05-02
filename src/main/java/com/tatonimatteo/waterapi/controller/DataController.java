package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.Data;
import com.tatonimatteo.waterapi.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @GetMapping("filter/station-date/{stationId}/{startDate}/{endDate}")
    public List<Data> getByStationIdAndDayBetween(
            @PathVariable Long stationId,
            @PathVariable Date startDate,
            @PathVariable Date endDate
    ) {
        return dataService.findByStationIdAndDayBetween(stationId, startDate, endDate);
    }

    @GetMapping("filter/sensor-date/{sensorId}/{startDate}/{endDate}")
    public List<Data> getBySensorIdAndDayBetween(
            @PathVariable Long sensorId,
            @PathVariable Date startDate,
            @PathVariable Date endDate
    ) {
        return dataService.findBySensorIdAndDayBetween(sensorId, startDate, endDate);
    }

    @GetMapping("filter/station/{stationId}")
    public List<Data> getByStationId(@PathVariable Long stationId) {
        return dataService.findByStationId(stationId);
    }


    @GetMapping("currentstate/{stationId}/{minuteRange}")
    public Integer getCurrentState(@PathVariable Long stationId, @PathVariable Long minuteRange) {
        return dataService.getCurrentState(stationId, minuteRange);
    }
}
