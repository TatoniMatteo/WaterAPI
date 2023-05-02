package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.Station;
import com.tatonimatteo.waterapi.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationsRepository;

    public List<Station> findAll() {
        return stationsRepository.findAll();
    }

}
