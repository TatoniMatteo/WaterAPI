package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.data.Station;
import com.tatonimatteo.waterapi.repository.data.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationsRepository;

    public List<Station> findAll() {
        return stationsRepository.findAll();
    }

    public Optional<Station> findById(long id) {
        return stationsRepository.findById(id);
    }
}
