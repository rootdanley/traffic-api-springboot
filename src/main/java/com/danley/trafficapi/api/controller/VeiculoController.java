package com.danley.trafficapi.api.controller;


import com.danley.trafficapi.api.model.VeiculoModel;
import com.danley.trafficapi.api.mapper.VeiculoAssembler;
import com.danley.trafficapi.api.model.input.VeiculoInput;
import com.danley.trafficapi.domain.model.Veiculo;
import com.danley.trafficapi.domain.repository.VeiculoRepository;
import com.danley.trafficapi.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

   
   private final VeiculoRepository veiculoRepository;
   private final RegistroVeiculoService registroVeiculoService;
   private final VeiculoAssembler veiculoAssembler;
   
   @GetMapping
   public List<VeiculoModel> listar() {
      return  veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
   }
   
   @GetMapping("/{veiculoId}")
   public ResponseEntity<VeiculoModel> buscarPorId(@PathVariable Long veiculoId) {
      
      return veiculoRepository.findById(veiculoId)
                 .map(veiculoAssembler::toModel)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
   }
   
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public VeiculoModel cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) {
      Veiculo novoVeiculo  = veiculoAssembler.toEntity(veiculoInput);
      Veiculo veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);
      
      return veiculoAssembler.toModel(veiculoCadastrado);
   }
   

}
