package com.tatonimatteo.waterapi.repository.data;

import com.tatonimatteo.waterapi.entity.data.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {

}
