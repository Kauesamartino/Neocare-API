package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.Sexo;

import java.time.LocalDate;

public record UsuarioOutputDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento,
        Sexo sexo,
        Integer altura,
        Double peso,
        EnderecoOutputDTO endereco,
        Boolean ativo
) {
    public UsuarioOutputDTO(Long id, String nome, String sobrenome, String cpf, String email, String telefone, LocalDate dataNascimento, Sexo sexo, Integer altura, Double peso, EnderecoOutputDTO endereco, Boolean ativo) {
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
}
