package com.example.temperaturemonitor.com.temperature.service;

import com.example.temperaturemonitor.com.temperature.entity.RegistrationReading;
import com.example.temperaturemonitor.com.temperature.entity.Sensor;
import com.example.temperaturemonitor.com.temperature.repository.RegistrationReadingRepository;
import com.example.temperaturemonitor.com.temperature.repository.SensorRepository;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistrationReadingService {

    private final RegistrationReadingRepository repository;
    private final SensorRepository sensorRepository;

    public RegistrationReadingService(RegistrationReadingRepository repository,
                                      SensorRepository sensorRepository) {
        this.repository = repository;
        this.sensorRepository = sensorRepository;
    }

    public RegistrationReading create(RegistrationReading registrationReading, Long sensorId) {
        Sensor sensorById = sensorRepository.getById(sensorId);

        registrationReading.setRegistrationDate(LocalDateTime.now());
        registrationReading.setSensor(sensorById);

        return repository.save(registrationReading);
    }

    public List<RegistrationReading> findAll() {
        return repository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public RegistrationReading findMaxTemperature(LocalDateTime before,
                                                  LocalDateTime after) {
        return repository.findMaxTemperature(before, after);
    }

    public RegistrationReading findMinTemperature(LocalDateTime before,
                                                  LocalDateTime after) {
        return repository.findMinTemperature(before, after);
    }

    public List<RegistrationReading> findAllByDate(LocalDateTime before,
                                                   LocalDateTime after,
                                                   String region) {
        return repository.findAllByDate(before, after, region);
    }
}
