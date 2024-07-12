package com.danley.trafficapi.api.controller;


import com.danley.trafficapi.domain.exception.NegocioException;
import com.danley.trafficapi.domain.model.Veiculo;
import com.danley.trafficapi.domain.repository.VeiculoRepository;
import com.danley.trafficapi.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

   
   private final VeiculoRepository veiculoRepository;
   private final RegistroVeiculoService registroVeiculoService;
   private final ModelMapper modelMapper;
   
   @GetMapping
   public List<Veiculo> listar() {
      return veiculoRepository.findAll();
   }
   
   @GetMapping("/{veiculoId}")
   public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long veiculoId) {
      
      return veiculoRepository.findById(veiculoId)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
   }
   
   
   @PostMapping
   public Veiculo cadastrar(@Valid @RequestBody Veiculo veiculo) {
      return registroVeiculoService.cadastrar(veiculo);
      
   }
   

}
