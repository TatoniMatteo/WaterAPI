package com.tatonimatteo.waterapi.repository;

import com.tatonimatteo.waterapi.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findBySensorIdAndDayBetween(long stationId, Date startDate, Date endDate);

    List<Data> findBySensorId(long stationId);
}
