package com.danley.trafficapi.domain.model;

import com.danley.trafficapi.domain.exception.NegocioException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {
   
   @EqualsAndHashCode.Include
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   

   @ManyToOne
   private Proprietario proprietario;
   
   private String marca;
   
   private String modelo;
   
   private String placa;
   

   @Enumerated(EnumType.STRING)
   private StatusVeiculo  status;

   private OffsetDateTime dataCadastro;

   private OffsetDateTime dataApreensao;
   
   @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
   private List<Autuacao> autuacoes = new ArrayList<>();
   
   
   public Autuacao adicionarAutuacao(Autuacao autuacao) {
      autuacao.setDataOcorrencia(OffsetDateTime.now());
      autuacao.setVeiculo(this);
      getAutuacoes().add(autuacao);
      
      return autuacao;
   }
   
   
   public void apreender(){
      if (estaApreendido()){
         throw new NegocioException("veiculo já apreendido");
      }
      
      setStatus(StatusVeiculo.APREENDIDO);
      setDataApreensao(OffsetDateTime.now());
   }
   
   public void removerApreensao(){
      if (naoEstaApreendido()){
         throw new NegocioException("veiculo nao esta apreendido");
      }
      
      setStatus(StatusVeiculo.REGULAR);
      setDataApreensao(null);
   }
   
   
   public boolean estaApreendido(){
      return StatusVeiculo.APREENDIDO.equals(getStatus());
   }
   
   public boolean naoEstaApreendido(){
      return !estaApreendido();
   }
   
}
