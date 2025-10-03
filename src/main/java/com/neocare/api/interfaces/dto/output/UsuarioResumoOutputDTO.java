package com.neocare.api.interfaces.dto.output;

public class UsuarioResumoOutputDTO {
    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;

    private String telefone;

    public UsuarioResumoOutputDTO(String nome, String sobrenome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
}
