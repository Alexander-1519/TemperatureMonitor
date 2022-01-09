package com.example.temperaturemonitor.com.temperature.controller;

import com.example.temperaturemonitor.com.temperature.entity.Sensor;
import com.example.temperaturemonitor.com.temperature.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> findAll() {
        List<Sensor> sensors = sensorService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(sensors);
    }

    @PostMapping("/{regionId}")
    public ResponseEntity<Sensor> create(@RequestBody Sensor sensor,
                                         @PathVariable Long regionId) {
        Sensor savedSensor = sensorService.create(sensor, regionId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedSensor);
    }
}
