package com.danley.trafficapi.api.dto;

import com.danley.trafficapi.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;


@Getter
@Setter
public class VeiculoDto {
   private Long id;
   private String nomeProprietario;
   private String marca;
   private String modelo;
   private String placa;
   private StatusVeiculo status;
   private OffsetDateTime dataCadastro;
   private OffsetDateTime dataApreensao;
}
