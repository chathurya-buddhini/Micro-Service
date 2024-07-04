package com.example.vehicle_service.service;



import com.example.vehicle_service.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> getAllVehicles();

    VehicleDTO getVehicleById(String id);

    VehicleDTO saveVehicle (VehicleDTO vehicleDTO);

    void updateVehicle(VehicleDTO vehicleDTO);

    void deleteVehicle(String id);
}
