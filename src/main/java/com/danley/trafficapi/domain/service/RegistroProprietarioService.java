package com.danley.trafficapi.domain.service;

import com.danley.trafficapi.domain.exception.NegocioException;
import com.danley.trafficapi.domain.model.Proprietario;
import com.danley.trafficapi.domain.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroProprietarioService {
   
   private final ProprietarioRepository proprietarioRepository;
   
   public RegistroProprietarioService(ProprietarioRepository proprietarioRepository) {
      this.proprietarioRepository = proprietarioRepository;
   }
   
   
   public Proprietario buscar(Long proprietarioId) {
      return proprietarioRepository.findById(proprietarioId)
                 .orElseThrow(() -> new NegocioException("proprietario nao encontrado"));
   }
   
   @Transactional // springframework transaction
   public Proprietario salvar(Proprietario proprietario) {
      
      boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                               .filter(p -> !p.equals(proprietario))
                               .isPresent();
      
      if (emailEmUso){
         throw new NegocioException("Email já cadastrado");
      }
      
      return proprietarioRepository.save(proprietario);
   }
   
   
   @Transactional // springframework transaction
   public void excluir(Long proprietarioId) {
      proprietarioRepository.deleteById(proprietarioId);
   }
}
