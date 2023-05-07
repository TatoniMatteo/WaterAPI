package com.tatonimatteo.waterapi.repository.data;

import com.tatonimatteo.waterapi.entity.data.Record;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Transactional("dataTransactionManager")
public interface RecordsRepository extends JpaRepository<Record, Long> {

    @Override
    @NotNull
    List<Record> findAll();

    List<Record> findBySensorIdAndDayBetween(long stationId, Date startDate, Date endDate);

    List<Record> findBySensorIdAndDayAndTimeAfter(long sensorId, Date day, Time startTime);

    List<Record> findBySensorIdAndDayAndTimeBefore(long sensorId, Date day, Time endTime);

    List<Record> findBySensorId(long stationId);
}
