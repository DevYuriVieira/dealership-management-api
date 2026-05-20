package com.dealership.api.repository;

import com.dealership.api.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    boolean existsByPlaca(String placa);

    Optional<Vehicle> findByPlacaIgnoreCase(String placa);

    List<Vehicle> findByMarcaIgnoreCase(String marca);

    List<Vehicle> findByModeloIgnoreCase(String modelo);
}
