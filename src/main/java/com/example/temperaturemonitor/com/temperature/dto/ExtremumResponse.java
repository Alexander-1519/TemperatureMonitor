package com.example.temperaturemonitor.com.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtremumResponse {

    private Double min;
    private Double max;
}
