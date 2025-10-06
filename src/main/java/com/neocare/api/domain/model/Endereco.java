package com.neocare.api.domain.model;

import com.neocare.api.domain.exception.ValidacaoDominioException;

import java.util.Locale;

public class Endereco {

    private String logradouro;

    private String bairro;

    private String cep;

    private String numero;

    private String complemento;

    private String cidade;

    private String uf;

    public Endereco(String logradouro, String bairro, String cep, String numero, String complemento, String cidade, String uf) {
        this.logradouro = logradouro;
        this.bairro = bairro;
        setCep(cep);
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        setUf(uf);
    }

    public void setCep(String cep) {
        this.cep = cep;
        isCepValido();
    }

    private void isCepValido() {
        if(!this.cep.matches("\\d{5}-\\d{3}")){
            throw new ValidacaoDominioException("CEP inválido, utilize o formato XXXXX-XXX");
        }
    }

    public void setUf(String uf) {
        this.uf = uf.toUpperCase(Locale.ROOT);
        isUfValido();
    }

    private void isUfValido() {
        if (uf.length() != 2) {
            throw new ValidacaoDominioException("Formato de UF inválido, formato deve ser XX");
        }
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public Endereco() {
    }
}
