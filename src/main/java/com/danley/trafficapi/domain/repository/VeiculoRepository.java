package com.danley.trafficapi.domain.repository;

import com.danley.trafficapi.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

   
   public Optional<Veiculo> findByPlaca(String placa);
}
