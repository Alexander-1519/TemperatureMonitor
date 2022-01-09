package com.example.temperaturemonitor.com.temperature.repository;

import com.example.temperaturemonitor.com.temperature.entity.Region;
import com.example.temperaturemonitor.com.temperature.entity.RegistrationReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistrationReadingRepository extends JpaRepository<RegistrationReading, Long> {

    @Query("""
            SELECT r FROM RegistrationReading r 
            WHERE r.temperature = (
                SELECT max(reg.temperature) from RegistrationReading reg 
                WHERE reg.registrationDate between :before AND :after
                                   )
            """)
    RegistrationReading findMaxTemperature(LocalDateTime before,
                                           LocalDateTime after);

    @Query("""
            SELECT r FROM RegistrationReading r 
            WHERE r.temperature = (
                SELECT min(reg.temperature) from RegistrationReading reg 
                WHERE reg.registrationDate between :before AND :after
                                   )
            """)
    RegistrationReading findMinTemperature(LocalDateTime before,
                                           LocalDateTime after);

    @Query("""
            FROM RegistrationReading r
            JOIN r.sensor s
            WHERE r.registrationDate between  :before AND :after
            AND lower(s.region.regionName) = lower(:region)
            """)
    List<RegistrationReading> findAllByDate(LocalDateTime before,
                                            LocalDateTime after,
                                            String region);
}
