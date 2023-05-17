package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.data.Sensor;
import com.tatonimatteo.waterapi.repository.data.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorsRepository;

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public List<Sensor> findByStationId(long stationId) {
        return sensorsRepository.findByStationId(stationId);
    }

    public String getName(long sensorId) {
        return sensorsRepository.findBySensorId(sensorId).getSensorType().getName();
    }
}
