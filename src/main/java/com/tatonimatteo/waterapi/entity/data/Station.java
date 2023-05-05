package com.tatonimatteo.waterapi.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dv_zetaced_station")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    private long id;

    private String name;

    @Column(name = "tel")
    private String phone;

    private String latitude;

    private String longitude;
}

