package com.danley.trafficapi.domain.service;


import com.danley.trafficapi.domain.model.StatusVeiculo;
import com.danley.trafficapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

   
   private final RegistroVeiculoService registroVeiculoService;
   
   @Transactional
   public void apreender(Long veiculoId){
      Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
      
      veiculo.apreender();
      
      veiculo.setStatus(StatusVeiculo.APREENDIDO);
   }
   
   @Transactional
   public void removerApreensao(Long veiculoId){
      Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
      
      veiculo.removerApreensao();
   }
}
