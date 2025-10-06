package com.neocare.api.infrastructure.entity;

import com.neocare.api.domain.enums.Sexo;
import com.neocare.api.domain.model.Endereco;
import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "usuario")
@Entity(name = "Usuario")
public class JpaUsuarioEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    protected JpaUsuarioEntity() {
    }

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

    public JpaUsuarioEntity(Long id, String nome, String sobrenome, String cpf, String email, String telefone, LocalDate dataNascimento, Sexo sexo, Integer altura, Double peso, Endereco endereco, Boolean ativo) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Integer getAltura() {
        return altura;
    }

    public Double getPeso() {
        return peso;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
