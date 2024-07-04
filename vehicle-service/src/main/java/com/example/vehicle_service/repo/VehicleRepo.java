package com.example.vehicle_service.repo;

import com.example.vehicle_service.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<VehicleEntity, String> {

}
