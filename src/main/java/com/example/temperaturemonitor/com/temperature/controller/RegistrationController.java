package com.example.temperaturemonitor.com.temperature.controller;

import com.example.temperaturemonitor.com.temperature.dto.ExtremumResponse;
import com.example.temperaturemonitor.com.temperature.dto.RegistrationDto;
import com.example.temperaturemonitor.com.temperature.dto.RegistrationResponse;
import com.example.temperaturemonitor.com.temperature.entity.RegistrationReading;
import com.example.temperaturemonitor.com.temperature.service.RegistrationReadingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/registrations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {

    private final RegistrationReadingService service;

    public RegistrationController(RegistrationReadingService service) {
        this.service = service;
    }

    @PostMapping("/{sensorId}")
    public ResponseEntity<RegistrationReading> create(@RequestBody RegistrationReading reading,
                                                      @PathVariable Long sensorId) {
        RegistrationReading registrationReading = service.create(reading, sensorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registrationReading);
    }

    @GetMapping
    public ResponseEntity<RegistrationResponse> findALl(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Long before,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Long after,
            @RequestParam String region) {
        List<RegistrationReading> registrationReadings = service.findAllByDate(
                Instant.ofEpochMilli(before).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                Instant.ofEpochMilli(after).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                region);
        List<RegistrationDto> registrationDtos = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for(RegistrationReading r: registrationReadings){
            RegistrationDto dto = new RegistrationDto(r.getRegistrationDate().toEpochSecond(ZoneOffset.UTC),
                    r.getTemperature(), r.getSensor().getId(), r.getSensor().getRegion().getRegionName());
            ids.add(r.getSensor().getId());
            registrationDtos.add(dto);
        }
        RegistrationResponse response = new RegistrationResponse(registrationDtos, ids);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/max")
    public ResponseEntity<RegistrationReading> findMaxTemperature(
            @RequestParam Long before,
            @RequestParam Long after) {
        RegistrationReading maxTemperature = service.findMaxTemperature(
                Instant.ofEpochMilli(before).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                Instant.ofEpochMilli(after).atZone(ZoneId.systemDefault()).toLocalDateTime());

        return ResponseEntity.status(HttpStatus.OK)
                .body(maxTemperature);
    }

    @GetMapping("/min")
    public ResponseEntity<RegistrationReading> findMinTemperature(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime before,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime after) {
        RegistrationReading maxTemperature = service.findMinTemperature(before, after);

        return ResponseEntity.status(HttpStatus.OK)
                .body(maxTemperature);
    }

    @GetMapping("/extremum")
    public ResponseEntity<ExtremumResponse> findExtremum(
            @RequestParam Long before,
            @RequestParam Long after){
        RegistrationReading minTemperature = service.findMinTemperature
                (Instant.ofEpochMilli(before).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                Instant.ofEpochMilli(after).atZone(ZoneId.systemDefault()).toLocalDateTime());
        RegistrationReading maxTemperature = service.findMaxTemperature
                (Instant.ofEpochMilli(before).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        Instant.ofEpochMilli(after).atZone(ZoneId.systemDefault()).toLocalDateTime());

        ExtremumResponse extremumResponse = new ExtremumResponse(
                minTemperature.getTemperature(),
                maxTemperature.getTemperature());

        return ResponseEntity.status(HttpStatus.OK)
                .body(extremumResponse);
    }
}
