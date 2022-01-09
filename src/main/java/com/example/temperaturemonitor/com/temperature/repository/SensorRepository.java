package com.example.temperaturemonitor.com.temperature.repository;

import com.example.temperaturemonitor.com.temperature.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
