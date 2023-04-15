package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.Data;
import com.tatonimatteo.waterapi.entity.Sensor;
import com.tatonimatteo.waterapi.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private SensorService sensorService;

    public List<Data> findByStationIdAndDayBetween(long stationId, Date startDate, Date endDate) {
        List<Data> result = new ArrayList<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            result.addAll(dataRepository.findBySensorIdAndDayBetween(sensor.getId(), startDate, endDate));
        }
        return result;
    }

    public List<Data> findBySensorIdAndDayBetween(long sensorId, Date startDate, Date endDate) {
        return dataRepository.findBySensorIdAndDayBetween(sensorId, startDate, endDate);
    }

    public List<Data> findByStationId(long stationId) {
        List<Data> result = new ArrayList<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            result.addAll(dataRepository.findBySensorId(sensor.getId()));
        }
        return result;
    }
}
