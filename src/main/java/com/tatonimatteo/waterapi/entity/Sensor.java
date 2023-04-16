package com.tatonimatteo.waterapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dv_zetaced_sensor")
public class Sensor {

    @Id
    private Long id;

    @OneToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SensorType sensorType;

    @Column(name = "sensor_id")
    private long sensorId;

    @Column(name = "station_id")
    private long stationId;

    @Column(name = "unit")
    private String unit;

    @Column(name = "decimals")
    private int decimals;

    public Long getId() {
        return id;
    }

    public String getName() {
        return sensorType.getName();
    }

    public long getSensorId() {
        return sensorId;
    }

    public long getStationId() {
        return stationId;
    }

    public String getUnit() {
        return unit;
    }

    public int getDecimals() {
        return decimals;
    }
}
