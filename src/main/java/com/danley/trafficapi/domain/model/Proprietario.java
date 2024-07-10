package com.danley.trafficapi.domain.model;

import com.danley.trafficapi.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name = "proprietario")
public class Proprietario {
   
   @NotNull(groups = ValidationGroups.ProprietarioId.class)
   @EqualsAndHashCode.Include
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @NotBlank
   @Size(max = 60)
   private String nome;
   
   @NotBlank
   @Size(max = 255)
   @Email
   private String email;
   
   @Column(name = "fone")
   private String telefone;
}