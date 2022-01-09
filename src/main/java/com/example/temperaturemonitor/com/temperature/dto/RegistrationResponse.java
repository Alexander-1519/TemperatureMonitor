package com.example.temperaturemonitor.com.temperature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {

    private List<RegistrationDto> registrations;
    private Set<Long> ids;
}
