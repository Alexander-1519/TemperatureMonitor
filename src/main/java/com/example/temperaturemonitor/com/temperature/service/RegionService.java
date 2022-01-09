package com.example.temperaturemonitor.com.temperature.service;

import com.example.temperaturemonitor.com.temperature.entity.Region;
import com.example.temperaturemonitor.com.temperature.repository.RegionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region create(Region region) {
        return regionRepository.save(region);
    }

    public List<Region> findAll() {
        return regionRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }
}
