package com.danley.trafficapi.api.controller;


import com.danley.trafficapi.api.assembler.AutuacaoAssembler;
import com.danley.trafficapi.api.model.AutuacaoModel;
import com.danley.trafficapi.api.model.input.AutuacaoInput;
import com.danley.trafficapi.domain.model.Autuacao;
import com.danley.trafficapi.domain.model.Veiculo;
import com.danley.trafficapi.domain.service.RegistroAutuacaoService;
import com.danley.trafficapi.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {
   
   private final RegistroAutuacaoService registroAutuacaoService;
   private final RegistroVeiculoService registroVeiculoService;
   private final AutuacaoAssembler autuacaoAssembler;
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public AutuacaoModel registrar(@PathVariable Long veiculoId , @Valid @RequestBody AutuacaoInput autuacaoInput){
      
      Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
      
      Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, novaAutuacao);
      return autuacaoAssembler.toModel(autuacaoRegistrada);
   }
   
   
   @GetMapping
   public List<AutuacaoModel> listar(@PathVariable Long veiculoId) {
      Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
      
      return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
   }
   
}
