package com.tatonimatteo.waterapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "dv_zetaced_data_sync")
public class Data {

    @Id
    private long id;

    @Column(name = "rdate")
    private Date day;

    @Column(name = "rtime")
    private Time time;

    @Column(name = "station_id")
    private long stationId;

    @Column(name = "sensor_id")
    private long sensorId;

    private Double value;

    public long getId() {
        return id;
    }

    public Date getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }

    public long getStationId() {
        return stationId;
    }

    public long getSensorId() {
        return sensorId;
    }

    public Double getValue() {
        return value;
    }
}

