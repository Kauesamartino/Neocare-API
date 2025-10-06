package com.neocare.api.interfaces.dto.output;

/**
 * Dados de saída para o resumo do usuário
 */
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
}
