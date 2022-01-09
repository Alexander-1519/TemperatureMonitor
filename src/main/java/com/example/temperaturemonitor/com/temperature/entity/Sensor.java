package com.example.temperaturemonitor.com.temperature.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sensorNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;

    private String name;

    private Double longitude;

    private Double latitude;
}
