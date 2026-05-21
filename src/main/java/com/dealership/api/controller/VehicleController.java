package com.dealership.api.controller;

import com.dealership.api.model.VehicleRequest;
import com.dealership.api.model.VehicleResponse;
import com.dealership.api.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> listarVeiculos() {
        List<VehicleResponse> veiculos = this.vehicleService.buscarTodos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<VehicleResponse>> buscarVeiculo(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {
        List<VehicleResponse> veiculos = this.vehicleService.buscarPorFiltros(placa, marca, modelo);
        return ResponseEntity.ok(veiculos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponse> atualizarVeiculo(@PathVariable UUID id, @Valid @RequestBody VehicleRequest request) {
        VehicleResponse response = this.vehicleService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable UUID id) {
        this.vehicleService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
