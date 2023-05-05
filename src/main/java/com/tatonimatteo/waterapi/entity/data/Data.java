package com.tatonimatteo.waterapi.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "dv_zetaced_data_sync")
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}

