package com.example.vehicle_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    @NotBlank(message = "vehicle ID can not be null")
    private String vehicleId;
    @NotBlank(message = "vehicle model can not be null")
    private String model;
    @NotBlank(message = "user ID can not be null")
    private String userid;
    @NotBlank(message = "registrationNumber can not be null")
    private String registrationNumber;
    private String make;
}
