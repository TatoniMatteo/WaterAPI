package com.tatonimatteo.waterapi.controller;

import com.tatonimatteo.waterapi.entity.data.Station;
import com.tatonimatteo.waterapi.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @GetMapping("/all")
    public List<Station> getAll() {
        return stationService.findAll();
    }
}
