package com.braincare.api.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {

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

    private Endereco endereco;

    public enum Sexo {
        MASCULINO,
        FEMININO,
        OUTROS
    }

    public Usuario(String nome, String sobrenome, String cpf, String email, String telefone, LocalDate dataNascimento, Sexo sexo, Integer altura, Double peso, Endereco endereco) {
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
    }

    public void setNome(String nome) {
        this.nome = nome;
        isNomeValido();
    }

    private void isNomeValido() {
        //valida se nome é nulo, vazio ou em branco
        if (nome == null || nome.isEmpty()) {
            throw new ValidacaoDominioException("Nome vazio");
        }

        //valida se a palavra tem no minimo 3 caracteres
        if(nome.length() < 3){
            throw new ValidacaoDominioException("Nome deve ter pelo menos 3 caracteres");
        }
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        isSobrenomeValido();
    }

    private void isSobrenomeValido() {
        //valida se nome é nulo, vazio ou em branco
        if (sobrenome == null || sobrenome.isEmpty()) {
            throw new ValidacaoDominioException("Sobrenome vazio");
        }

        //valida se a palavra te no minimo 3 caracteres
        if(sobrenome.length() < 3){
            throw new ValidacaoDominioException("Sobrenome deve ter pelo menos 3 caracteres");
        }
    }

    public void setCpf(String cpf) {
        // Remove caracteres que não sejam números
        this.cpf = cpf.replaceAll("\\D", "");
        isCpfValido(this.cpf);
    }

    private void isCpfValido(String cpf) {
        //regex valida se cpf só contem numeros
        if (!cpf.matches("\\d{11}")) {
            throw new ValidacaoDominioException("CPF deve conter apenas números");
        }

        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            throw new ValidacaoDominioException("CPF deve ter 11 dígitos");
        }

        // Elimina CPFs com todos os dígitos iguais (ex: 00000000000, 11111111111, etc.)
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new ValidacaoDominioException("CPF inválido por ter todos os dígitos iguais");
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int resto = 11 - (soma % 11);
        int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        resto = 11 - (soma % 11);
        int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

        // Confere se os dígitos calculados são iguais aos do CPF informado
        boolean valido = digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0');

        if (!valido) {
            throw new ValidacaoDominioException("CPF inválido");
        }
    }

    public void setEmail(String email) {
        this.email = email;
        isEmailValido();
    }

    private void isEmailValido() {
        final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(regex)) {
            throw new ValidacaoDominioException("Email inválido");
        }
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        isTelefoneValido();
    }

    private void isTelefoneValido() {
        //regex para validar se telefone (xx)xxxxx-xxxx
        final String regex = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$";
        if (!telefone.matches(regex)) {
            throw new ValidacaoDominioException("Telefone inválido, utilize o formato (DD) XXXXX-XXXX");
        }
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        isDataNascimentoValido();
    }

    private void isDataNascimentoValido() {
        if  (dataNascimento == null) {
            throw new ValidacaoDominioException("É necessário informar a data de nascimento");
        }

        LocalDate hoje = LocalDate.now();
        LocalDate idadeMinima = hoje.minusDays(18);

        if (dataNascimento.isAfter(idadeMinima)) {
            throw new ValidacaoDominioException("É necessário ter pelo menos 18 anos");
        }
    }

}
