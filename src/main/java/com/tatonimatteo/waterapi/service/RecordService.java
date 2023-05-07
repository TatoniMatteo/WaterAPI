package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.data.Record;
import com.tatonimatteo.waterapi.entity.data.Sensor;
import com.tatonimatteo.waterapi.repository.data.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final int ERROR = 0;
    private final int BAD = 1;
    private final int GOOD = 2;
    private final int EXCELLENT = 3;

    private final RecordsRepository dataRepository;

    private final SensorService sensorService;

    public List<Record> findByStationIdAndDayBetween(
            long stationId,
            @NotNull Date startDate,
            @NotNull Date endDate
    ) {
        List<Record> result = new ArrayList<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            result.addAll(dataRepository.findBySensorIdAndDayBetween(sensor.getId(), startDate, endDate));
        }
        return result;
    }

    public List<Record> findBySensorIdAndDayBetween(
            long sensorId,
            @NotNull Date startDate,
            @NotNull Date endDate
    ) {
        return dataRepository.findBySensorIdAndDayBetween(sensorId, startDate, endDate);
    }

    public List<Record> findByStationId(long stationId) {
        List<Record> result = new ArrayList<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            result.addAll(dataRepository.findBySensorId(sensor.getId()));
        }
        return result;
    }

    /**
     * This function performs an analysis of the latest records collected (last 30 minutes) by the station identified by {@code stationID}
     * and returns a value indicating the water quality
     *
     * @param stationId the ID of the interested station
     * @return Integer number between 0 and 3:
     * <ul>
     *  <li>0 = Error. Need moore data</li>
     *  <li>1 = Bad state of water</li>
     *  <li>2 = Acceptable condition of water</li>
     *  <li>3 = Excellent condition of water</li>
     * </ul>
     */
    public Integer getCurrentState(long stationId, long minuteRange) {
        long rangeTime = 60000 * minuteRange;

        //long currentTime = System.currentTimeMillis();
        long currentMillis = 1675984500000L; // TEST (2023-02-10 - 00:15:00)
        long startMillis = currentMillis - rangeTime;

        Date endDate = new Date(currentMillis);
        Time endTime = new Time(currentMillis);
        Date startDate = new Date(startMillis);
        Time startTime = new Time(startMillis);

        Map<Long, Double> lastData = new HashMap<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            lastData.put(
                    sensor.getSensorId(),
                    getLastValue(sensor.getId(), startDate, endDate, startTime, endTime)
            );

        }
        return lastData.size();     // TODO{corregge con il valore corretto calcolato dall'algoritmo}
    }


    /**
     * This function searches in a given period of time (from {@code startDate:startTime} to {@code endDate:endTime})
     * and returns the last value recorded by the sensor identified by {@code sensorId}
     *
     * @param sensorId  the ID of the interested sensor
     * @param startDate the day from which to start the search
     * @param endDate   the date on which to end the search
     * @param startTime the time from which to start the search
     * @param endTime   the time to end the search
     * @return The last value recorded by the sensor in the indicated time span or
     * {@code null} if there are no measurements in that time span
     */
    private Double getLastValue(
            long sensorId,
            @NotNull Date startDate,
            @NotNull Date endDate,
            @NotNull Time startTime,
            @NotNull Time endTime
    ) {
        List<Record> records;
        if (!startDate.toLocalDate().isEqual(endDate.toLocalDate())) {
            records = (dataRepository.findBySensorIdAndDayAndTimeBefore(sensorId, endDate, endTime));
            if (records.isEmpty())
                records = dataRepository.findBySensorIdAndDayAndTimeAfter(sensorId, startDate, startTime);
        } else {
            records = dataRepository.findBySensorIdAndDayAndTimeAfter(sensorId, startDate, startTime);
        }

        if (records.size() == 1) return records.get(0).getValue();
        return records
                .stream()
                .max(Comparator.comparing(Record::getTime))
                .map(Record::getValue)
                .orElse(null);
    }
}