package com.example.temperaturemonitor.com.temperature.controller;

import com.example.temperaturemonitor.com.temperature.entity.Region;
import com.example.temperaturemonitor.com.temperature.service.RegionService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> findAll() {
        List<Region> regions = regionService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(regions);
    }

    @PostMapping
    public ResponseEntity<Region> create(@RequestBody Region region) {
        Region savedRegion = regionService.create(region);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedRegion);
    }
}
