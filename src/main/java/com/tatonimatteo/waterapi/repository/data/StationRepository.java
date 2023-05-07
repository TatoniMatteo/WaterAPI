package com.tatonimatteo.waterapi.repository.data;

import com.tatonimatteo.waterapi.entity.data.Station;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("dataTransactionManager")
public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    @NotNull
    List<Station> findAll();
}
