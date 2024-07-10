package com.danley.trafficapi.domain.repository;

import com.danley.trafficapi.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
   
   
   Optional<Proprietario> findByEmail(String email);
}
