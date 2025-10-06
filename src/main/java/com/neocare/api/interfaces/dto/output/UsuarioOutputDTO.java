package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.Sexo;

import java.time.LocalDate;

/**
 * Dados de saída para o usuário
 */
public class UsuarioOutputDTO {

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String telefone;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private Integer altura;

    private Double peso;

    private EnderecoOutputDTO endereco;

    private Boolean ativo;

    public UsuarioOutputDTO(String nome, String sobrenome, String cpf, String email, String telefone, LocalDate dataNascimento, Sexo sexo, Integer altura, Double peso, EnderecoOutputDTO endereco, Boolean ativo) {
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

    public EnderecoOutputDTO getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
