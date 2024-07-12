package com.danley.trafficapi.api.mapper;

import com.danley.trafficapi.api.model.VeiculoModel;
import com.danley.trafficapi.api.model.input.VeiculoInput;
import com.danley.trafficapi.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {
   
   private final ModelMapper modelMapper;
   
   public Veiculo toEntity(VeiculoInput veiculoInput) {
      return modelMapper.map(veiculoInput, Veiculo.class);
   }
   
   
   public VeiculoModel toModel(Veiculo veiculo) {
      return modelMapper.map(veiculo, VeiculoModel.class);
   }
   
   public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos) {
      return veiculos.stream()
                 .map(this::toModel)
                 .toList();
   }
}
