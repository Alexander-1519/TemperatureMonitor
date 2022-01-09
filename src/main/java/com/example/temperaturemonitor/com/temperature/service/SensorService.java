package com.example.temperaturemonitor.com.temperature.service;

import com.example.temperaturemonitor.com.temperature.entity.Region;
import com.example.temperaturemonitor.com.temperature.entity.Sensor;
import com.example.temperaturemonitor.com.temperature.repository.RegionRepository;
import com.example.temperaturemonitor.com.temperature.repository.SensorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final RegionRepository regionRepository;

    public SensorService(SensorRepository sensorRepository,
                         RegionRepository regionRepository) {
        this.sensorRepository = sensorRepository;
        this.regionRepository = regionRepository;
    }

    public Sensor create(Sensor sensor, Long regionId) {
        Region region = regionRepository.findById(regionId).get();

        sensor.setRegion(region);
        return sensorRepository.save(sensor);
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }
}
