package com.example.vehicle_service.api;


import com.example.vehicle_service.dto.VehicleDTO;
import com.example.vehicle_service.service.VehicleService;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {


    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<VehicleDTO> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping(path = "/{id}")
    public VehicleDTO getVehicleByID(@PathVariable("id") String id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDTO saveVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        return vehicleService.saveVehicle(vehicleDTO);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVehicle(@Valid @RequestBody VehicleDTO vehicleDTO){
        vehicleService.updateVehicle(vehicleDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable("id") String id){
        vehicleService.deleteVehicle(id);
    }
}
