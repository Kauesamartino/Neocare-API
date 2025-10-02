package com.neocare.api.infrastructure.entity;

import com.neocare.api.domain.enums.Sexo;
import com.neocare.api.domain.model.Endereco;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaUsuarioEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private Integer altura;

    private Double peso;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public JpaUsuarioEntity(String nome, String sobrenome, String cpf, String email, String telefone, LocalDate dataNascimento, Sexo sexo, Integer altura, Double peso, Endereco endereco, Boolean ativo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.endereco = endereco;
        this.ativo = ativo;
    }
}
