package com.dealership.api.controller;

import com.dealership.api.model.VehicleRequest;
import com.dealership.api.model.VehicleResponse;
import com.dealership.api.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculo")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> cadastrarVeiculo(@Valid @RequestBody VehicleRequest request) {
        VehicleResponse response = this.vehicleService.inserir(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
