package com.tatonimatteo.waterapi.repository;

import com.tatonimatteo.waterapi.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

}
