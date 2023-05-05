package com.tatonimatteo.waterapi.entity.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dv_zetaced_sensor_type")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorType {

    @Id
    private long id;

    private String name;
}
