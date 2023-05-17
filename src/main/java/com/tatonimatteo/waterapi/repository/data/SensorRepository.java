package com.tatonimatteo.waterapi.repository.data;

import com.tatonimatteo.waterapi.entity.data.Sensor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("dataTransactionManager")
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Override
    @NotNull
    List<Sensor> findAll();

    List<Sensor> findByStationId(Long stationId);

    Sensor findBySensorId(long sensorId);
}
