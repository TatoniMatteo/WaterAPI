package com.tatonimatteo.waterapi.service;

import com.tatonimatteo.waterapi.entity.Station;
import com.tatonimatteo.waterapi.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationRepository stationsRepository;

    public List<Station> findAll() {
        return stationsRepository.findAll();
    }

}
