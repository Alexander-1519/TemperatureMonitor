package com.example.temperaturemonitor.com.temperature.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_number")
    private Long regionNumber;

    @Column(name = "region_name")
    private String regionName;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private List<Sensor> sensors;
}
