package com.example.temperaturemonitor.com.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private Long date;
    private Double temperature;
    private Long sensorId;
    private String region;
}
