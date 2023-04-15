package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.Sensor;
import com.tatonimatteo.waterapi.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorsRepository;

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public List<Sensor> findByStationId(Long stationId) {
        return sensorsRepository.findByStationId(stationId);
    }
}
