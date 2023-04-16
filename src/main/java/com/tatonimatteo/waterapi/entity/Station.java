package com.tatonimatteo.waterapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dv_zetaced_station")
public class Station {

    @Id
    private long id;

    private String name;

    @Column(name = "tel")
    private String phone;

    private String latitude;

    private String longitude;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

