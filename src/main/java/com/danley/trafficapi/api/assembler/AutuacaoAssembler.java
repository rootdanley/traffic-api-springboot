package com.danley.trafficapi.api.assembler;


import com.danley.trafficapi.api.model.AutuacaoModel;
import com.danley.trafficapi.api.model.input.AutuacaoInput;
import com.danley.trafficapi.domain.model.Autuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {
   
   private final ModelMapper modelMapper;
   
   
   public AutuacaoModel toModel(Autuacao autuacao){
      
      return modelMapper.map(autuacao, AutuacaoModel.class);
   }
   
   public List<AutuacaoModel> toCollectionModel(List<Autuacao> atuacoes){
      
      return atuacoes.stream()
                 .map(this::toModel)
                 .toList();
   }
   
   public Autuacao toEntity(AutuacaoInput autuacaoInput){
      
      return modelMapper.map(autuacaoInput, Autuacao.class);
   }
}


