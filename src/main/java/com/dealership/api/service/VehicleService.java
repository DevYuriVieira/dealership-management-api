package com.dealership.api.service;

import com.dealership.api.entity.Client;
import com.dealership.api.entity.Vehicle;
import com.dealership.api.exception.DuplicateResourceException;
import com.dealership.api.exception.ResourceNotFoundException;
import com.dealership.api.model.VehicleRequest;
import com.dealership.api.model.VehicleResponse;
import com.dealership.api.repository.ClientRepository;
import com.dealership.api.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;

    public VehicleService(VehicleRepository vehicleRepository, ClientRepository clientRepository) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
    }

    public VehicleResponse inserir (VehicleRequest request) {
        if (this.vehicleRepository.existsByPlaca(request.placa())) {
            throw new DuplicateResourceException("Já existe um veículo cadastrado com a placa: " + request.placa());
        }

        Client donoDoVeiculo = this.clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + request.clientId()));

        Vehicle vehicle = new Vehicle();
        vehicle.setPlaca(request.placa());
        vehicle.setMarca(request.marca());
        vehicle.setModelo(request.modelo());
        vehicle.setAno(request.ano());
        vehicle.setClient(donoDoVeiculo);

        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        return new VehicleResponse(
                savedVehicle.getId(),
                savedVehicle.getPlaca(),
                savedVehicle.getMarca(),
                savedVehicle.getModelo(),
                savedVehicle.getAno(),
                savedVehicle.getClient().getId()
        );
    }
}
