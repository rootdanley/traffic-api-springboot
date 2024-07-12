package com.danley.trafficapi.domain.service;

import com.danley.trafficapi.domain.exception.NegocioException;
import com.danley.trafficapi.domain.model.Proprietario;
import com.danley.trafficapi.domain.model.StatusVeiculo;
import com.danley.trafficapi.domain.model.Veiculo;
import com.danley.trafficapi.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;


@AllArgsConstructor
@Service
public class RegistroVeiculoService {

   private final VeiculoRepository veiculoRepository;
   private final RegistroProprietarioService registroProprietarioService;

   @Transactional
   public Veiculo cadastrar(Veiculo novoVeiculo) {
      if (novoVeiculo.getId() != null) {
         throw new NegocioException("veiculo a ser cadastrado nao deve possuir um id");
      }
      
      
      boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca()).filter(veiculo -> !veiculo.equals(novoVeiculo)).isPresent();
      
      if (placaEmUso) {
         throw new NegocioException("ja existe veiculo cadastrado com esta placa");
      }
      
      Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());
      novoVeiculo.setProprietario(proprietario);
      novoVeiculo.setStatus(StatusVeiculo.REGULAR);
      novoVeiculo.setDataCadastro(OffsetDateTime.now());
      return veiculoRepository.save(novoVeiculo);
   }
}
