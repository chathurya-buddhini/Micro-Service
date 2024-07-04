package com.example.vehicle_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class VehicleEntity {
    @Id
    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "model")
    private String model;

    @Column(name = "user_nic")
    private String userid;

    @Column(name = "registration_num")
    private String registrationNumber;

    @Column(name = "make")
    private String make;


}
