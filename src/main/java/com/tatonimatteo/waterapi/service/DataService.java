package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.Data;
import com.tatonimatteo.waterapi.entity.Sensor;
import com.tatonimatteo.waterapi.repository.DataRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private SensorService sensorService;

    public List<Data> findByStationIdAndDayBetween(long stationId, @NotNull Date startDate, @NotNull Date endDate) {
        List<Data> result = new ArrayList<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            result.addAll(dataRepository.findBySensorIdAndDayBetween(sensor.getId(), startDate, endDate));
        }
        return result;
    }

    public List<Data> findBySensorIdAndDayBetween(long sensorId, @NotNull Date startDate, @NotNull Date endDate) {
        return dataRepository.findBySensorIdAndDayBetween(sensorId, startDate, endDate);
    }

    public List<Data> findByStationId(long stationId) {
        List<Data> result = new ArrayList<>();
        for (Sensor sensor : sensorService.findByStationId(stationId)) {
            result.addAll(dataRepository.findBySensorId(sensor.getId()));
        }
        return result;
    }

    /**
     * This class performs an analysis of the latest data collected by the station identified by {@code stationID}
     * today and returns a value indicating the water quality
     *
     * @param stationId the ID of the interested station
     * @return integer number between 0 and 1
     * 0 = Error. Need moore data
     * 1 = Bad state of water
     * 2 = Acceptable condition of water
     * 3 = Excellent condition of water
     */
    public Integer getCurrentState(long stationId) {
        int minuteRange = 30;
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

    private Double getLastValue(
            long sensorId,
            @NotNull Date startDate,
            @NotNull Date currentDate,
            @NotNull Time startTime,
            @NotNull Time currentTime
    ) {
        List<Data> data;
        if (!startDate.toLocalDate().isEqual(currentDate.toLocalDate())) {
            data = (dataRepository.findBySensorIdAndDayAndTimeBefore(sensorId, currentDate, currentTime));
            if (data.isEmpty())
                data = dataRepository.findBySensorIdAndDayAndTimeAfter(sensorId, startDate, startTime);
        } else {
            data = dataRepository.findBySensorIdAndDayAndTimeAfter(sensorId, startDate, startTime);
        }

        if (data.size() == 1) return data.get(0).getValue();
        return data
                .stream()
                .max(Comparator.comparing(Data::getTime))
                .map(Data::getValue)
                .orElse(null);
    }
}
