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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;

    public VehicleService(VehicleRepository vehicleRepository, ClientRepository clientRepository) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
    }

    public VehicleResponse inserir(VehicleRequest request) {
        String placaPadronizada = request.placa() != null ? request.placa().toUpperCase() : null;

        if (Boolean.TRUE.equals(request.vendido()) && request.valorVenda() == null) {
            throw new IllegalArgumentException("Informe o valor da venda para registrar o veículo como vendido.");
        }
        if (Boolean.FALSE.equals(request.vendido()) && request.valorVenda() != null) {
            throw new IllegalArgumentException("Um veículo não vendido não pode ter um valor de venda estipulado.");
        }

        if (this.vehicleRepository.existsByPlaca(placaPadronizada)) {
            throw new DuplicateResourceException("Já existe um veículo cadastrado com a placa: " + placaPadronizada);
        }

        Client donoDoVeiculo = this.clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + request.clientId()));

        Vehicle vehicle = new Vehicle();
        vehicle.setPlaca(placaPadronizada);
        vehicle.setMarca(request.marca());
        vehicle.setModelo(request.modelo());
        vehicle.setAno(request.ano());
        vehicle.setValor(request.valor());
        vehicle.setMaximoDesconto(request.maximoDesconto());
        vehicle.setVendido(request.vendido());
        vehicle.setValorVenda(request.valorVenda());
        vehicle.setClient(donoDoVeiculo);

        Vehicle savedVehicle = this.vehicleRepository.save(vehicle);

        return mapToResponse(savedVehicle);
    }

    public List<VehicleResponse> buscarTodos() {
        return this.vehicleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<VehicleResponse> buscarPorFiltros(String placa, String marca, String modelo) {
        if (placa != null && !placa.isBlank()) {
            return this.vehicleRepository.findByPlacaIgnoreCase(placa)
                    .map(this::mapToResponse)
                    .map(List::of)
                    .orElse(List.of());
        } else if (marca != null && !marca.isBlank()) {
            return this.vehicleRepository.findByMarcaIgnoreCase(marca)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } else if (modelo != null && !modelo.isBlank()) {
            return this.vehicleRepository.findByModeloIgnoreCase(modelo)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }
        return buscarTodos();
    }

    public VehicleResponse atualizar(UUID id, VehicleRequest request) {
        String placaPadronizada = request.placa() != null ? request.placa().toUpperCase() : null;

        if (Boolean.TRUE.equals(request.vendido()) && request.valorVenda() == null) {
            throw new IllegalArgumentException("Informe o valor da venda para registrar o veículo como vendido.");
        }
        if (Boolean.FALSE.equals(request.vendido()) && request.valorVenda() != null) {
            throw new IllegalArgumentException("Um veículo não vendido não pode ter um valor de venda estipulado.");
        }

        Vehicle vehicle = this.vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com o ID: " + id));

        if (!vehicle.getPlaca().equalsIgnoreCase(placaPadronizada) && this.vehicleRepository.existsByPlaca(placaPadronizada)) {
            throw new DuplicateResourceException("Já existe outro veículo cadastrado com a placa: " + placaPadronizada);
        }

        Client donoDoVeiculo = this.clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + request.clientId()));

        vehicle.setPlaca(placaPadronizada);
        vehicle.setMarca(request.marca());
        vehicle.setModelo(request.modelo());
        vehicle.setAno(request.ano());
        vehicle.setValor(request.valor());
        vehicle.setMaximoDesconto(request.maximoDesconto());
        vehicle.setVendido(request.vendido());
        vehicle.setValorVenda(request.valorVenda());
        vehicle.setClient(donoDoVeiculo);

        Vehicle updatedVehicle = this.vehicleRepository.save(vehicle);

        return mapToResponse(updatedVehicle);
    }

    public void deletar(UUID id) {
        Vehicle vehicle = this.vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com o ID: " + id));
        this.vehicleRepository.delete(vehicle);
    }

    private VehicleResponse mapToResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getPlaca(),
                vehicle.getMarca(),
                vehicle.getModelo(),
                vehicle.getAno(),
                vehicle.getValor(),
                vehicle.getMaximoDesconto(),
                vehicle.getVendido(),
                vehicle.getValorVenda(),
                vehicle.getClient().getId()
        );
    }
}
