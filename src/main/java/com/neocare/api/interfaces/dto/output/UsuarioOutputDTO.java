package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.Sexo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UsuarioOutputDTO {

    private Long id;

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

}
