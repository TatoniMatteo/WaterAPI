package com.tatonimatteo.waterapi.entity.support;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "record_range")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordRange {

    @Id
    private long sensorId;

    private double min;

    private double max;
}
