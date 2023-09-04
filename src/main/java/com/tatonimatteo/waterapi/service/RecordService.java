package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.data.Record;
import com.tatonimatteo.waterapi.entity.data.Sensor;
import com.tatonimatteo.waterapi.entity.support.DateRange;
import com.tatonimatteo.waterapi.entity.support.RecordRange;
import com.tatonimatteo.waterapi.repository.data.RecordsRepository;
import com.tatonimatteo.waterapi.repository.support.RecordRangeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordsRepository dataRepository;

    private final SensorService sensorService;

    private final RecordRangeRepository recordRangeRepository;

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
     * Calculates the date range based on the specified time range.
     *
     * @param minuteRange the time range in milliseconds
     * @return a DateRange object containing the start and end dates and times
     */
    private DateRange calculateDateRange(long minuteRange) {
        long rangeTime = 60000 * minuteRange;
        //long currentMillis = 1683677700000L; // TEST (2023-05-10 - 00:15:00)
        long currentMillis = System.currentTimeMillis();
        long startMillis = currentMillis - rangeTime;

        Date endDate = new Date(currentMillis);
        Time endTime = new Time(currentMillis);
        Date startDate = new Date(startMillis);
        Time startTime = new Time(startMillis);

        return new DateRange(startDate, endDate, startTime, endTime);
    }

    /**
     * Retrieves records of sensors associated with the station identified by {@code stationId}
     * where the sensor measurements fall outside the specified range.
     *
     * @param stationId   the ID of the station of interest
     * @param minuteRange the time range in minutes for which to analyze the records
     * @return a list containing the records that have measurements outside the specified range
     */
    public List<Record> getValuesOutOfRange(long stationId, long minuteRange) {
        DateRange dateRange = calculateDateRange(minuteRange);

        List<RecordRange> ranges = recordRangeRepository.findAll();
        List<Record> valuesOutOfRange = new ArrayList<>();

        List<Sensor> sensors = sensorService.findByStationId(stationId);

        for (Sensor sensor : sensors) {
            for (RecordRange range : ranges) {
                if (range.getSensorId() == sensor.getSensorId()) {
                    Record lastValue = getLastValueInRange(sensor.getId(), dateRange);
                    if (lastValue != null && (range.getMin() >= lastValue.getValue() || range.getMax() <= lastValue.getValue())) {
                        valuesOutOfRange.add(lastValue);
                    }
                    ranges.remove(range);
                    break;
                }
            }
        }
        return valuesOutOfRange;
    }

    /**
     * Retrieves the latest recorded values for each sensor associated with the station identified by {@code stationId}.
     *
     * @param stationId   the ID of the station of interest
     * @param minuteRange the time range in minutes for which to retrieve the latest values
     * @return a list containing the latest recorded values for all sensors associated with the station
     */
    public List<Record> getLatestValues(long stationId, long minuteRange) {
        DateRange dateRange = calculateDateRange(minuteRange);

        List<Sensor> sensors = sensorService.findByStationId(stationId);
        List<Record> latestValues = new ArrayList<>();

        for (Sensor sensor : sensors) {
            Record lastValue = getLastValueInRange(sensor.getId(), dateRange);
            if (lastValue != null) {
                latestValues.add(lastValue);
            }
        }

        return latestValues;
    }

    /**
     * Retrieves the last recorded value within the specified date range for the sensor identified by {@code sensorId}.
     *
     * @param sensorId  the ID of the interested sensor
     * @param dateRange the date range within which to search for the last value
     * @return the last recorded value by the sensor in the indicated date range, or
     * {@code null} if there are no measurements in that date range
     */
    private Record getLastValueInRange(long sensorId, DateRange dateRange) {
        List<Record> records;
        if (!dateRange.startDate().toLocalDate().isEqual(dateRange.endDate().toLocalDate())) {
            records = dataRepository.findBySensorIdAndDayAndTimeBefore(sensorId, dateRange.endDate(), dateRange.endTime());
            if (records.isEmpty())
                records = dataRepository.findBySensorIdAndDayAndTimeAfter(sensorId, dateRange.startDate(), dateRange.startTime());
        } else {
            records = dataRepository.findBySensorIdAndDayAndTimeAfter(sensorId, dateRange.startDate(), dateRange.startTime());
        }

        if (records.size() == 1) return records.get(0);
        return records
                .stream()
                .max(Comparator.comparing(Record::getTime))
                .orElse(null);
    }
}