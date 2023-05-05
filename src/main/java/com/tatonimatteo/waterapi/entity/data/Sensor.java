package com.tatonimatteo.waterapi.entity.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dv_zetaced_sensor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {

    @Id
    private Long id;

    @OneToOne()
    @JoinColumn(
            name = "sensor_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private SensorType sensorType;

    @Column(name = "sensor_id")
    private long sensorId;

    @Column(name = "station_id")
    private long stationId;

    @Column(name = "unit")
    private String unit;

    @Column(name = "decimals")
    private int decimals;
}
