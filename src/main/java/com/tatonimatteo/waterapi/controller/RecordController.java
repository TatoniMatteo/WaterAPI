package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.data.Record;
import com.tatonimatteo.waterapi.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService dataService;

    @GetMapping("filter/station-date/{stationId}/{startDate}/{endDate}")
    public List<Record> getByStationIdAndDayBetween(
            @PathVariable Long stationId,
            @PathVariable Date startDate,
            @PathVariable Date endDate
    ) {
        return dataService.findByStationIdAndDayBetween(stationId, startDate, endDate);
    }

    @GetMapping("filter/sensor-date/{sensorId}/{startDate}/{endDate}")
    public List<Record> getBySensorIdAndDayBetween(
            @PathVariable Long sensorId,
            @PathVariable Date startDate,
            @PathVariable Date endDate
    ) {
        return dataService.findBySensorIdAndDayBetween(sensorId, startDate, endDate);
    }

    @GetMapping("filter/station/{stationId}")
    public List<Record> getByStationId(@PathVariable Long stationId) {
        return dataService.findByStationId(stationId);
    }


    @GetMapping("currentstate/{stationId}/{minuteRange}")
    public Map<String, Double> getCurrentState(@PathVariable Long stationId, @PathVariable Long minuteRange) {
        return dataService.getCurrentState(stationId, minuteRange);
    }
}
