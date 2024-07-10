package com.danley.trafficapi.api.controller;

import com.danley.trafficapi.domain.exception.NegocioException;
import com.danley.trafficapi.domain.model.Proprietario;
import com.danley.trafficapi.domain.repository.ProprietarioRepository;
import com.danley.trafficapi.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

   private final RegistroProprietarioService registroProprietarioService;
  private final ProprietarioRepository proprietarioRepository;
  
  
  @GetMapping()
   public List<Proprietario> listar() {
     return proprietarioRepository.findAll();
  }
  
  @GetMapping("/{proprietarioId}")
  public ResponseEntity<Proprietario> buscarPorId(@PathVariable Long proprietarioId) {
     Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);
     
     return proprietario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
     
     return registroProprietarioService.salvar(proprietario);
  }
  
  @PutMapping("/{proprietarioId}")
  public ResponseEntity<Proprietario> atualizar(
      @PathVariable Long proprietarioId,
      @Valid @RequestBody Proprietario proprietario)
  {
     if (!proprietarioRepository.existsById(proprietarioId)) {
        return ResponseEntity.notFound().build();
     }
     
     proprietario.setId(proprietarioId);
     Proprietario proprietario1 = registroProprietarioService.salvar(proprietario);
     
     return ResponseEntity.ok(proprietario1);
  }
  
  
  @DeleteMapping("/{proprietarioId}")
  public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
     if (!proprietarioRepository.existsById(proprietarioId)) {
         return ResponseEntity.notFound().build();
     }
     
     this.registroProprietarioService.excluir(proprietarioId);
     return ResponseEntity.noContent().build();
  }

}
