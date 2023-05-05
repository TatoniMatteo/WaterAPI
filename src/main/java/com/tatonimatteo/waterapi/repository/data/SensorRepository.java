package com.tatonimatteo.waterapi.repository.data;

import com.tatonimatteo.waterapi.entity.data.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByStationId(Long stationId);
}
