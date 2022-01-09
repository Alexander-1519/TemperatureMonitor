package com.example.temperaturemonitor.com.temperature.repository;

import com.example.temperaturemonitor.com.temperature.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
