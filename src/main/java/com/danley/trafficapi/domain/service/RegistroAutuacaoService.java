package com.danley.trafficapi.domain.service;

import com.danley.trafficapi.domain.model.Autuacao;
import com.danley.trafficapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {
   
   private RegistroVeiculoService registroVeiculoService;
   
   @Transactional
   public Autuacao registrar(Long veiculoId, Autuacao novoAutuacao) {
      Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
      
      return veiculo.adicionarAutuacao(novoAutuacao);
   }
}
