package com.braincare.api.domain.model;

import com.braincare.api.domain.exception.ValidacaoDominioException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    private Boolean ativo;

    public enum Sexo {
        MASCULINO,
        FEMININO,
        OUTROS,
        NAO_INFORMADO
    }

    public Usuario(String nome, String sobrenome, String cpf, String email, String telefone, LocalDate dataNascimento, Sexo sexo, Integer altura, Double peso, Endereco endereco) {
        setNome(nome);
        setSobrenome(sobrenome);
        setCpf(cpf);
        setEmail(email);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
        setSexo(sexo);
        setAltura(altura);
        setPeso(peso);
        setEndereco(endereco);
        this.ativo = true;
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
        LocalDate idadeMinima = hoje.minusYears(18);

        if (dataNascimento.isAfter(idadeMinima)) {
            throw new ValidacaoDominioException("É necessário ter pelo menos 18 anos");
        }
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        isEnderecoValido();
    }

    private void isEnderecoValido() {
        if  (endereco == null) {
            throw new ValidacaoDominioException("É necessário informar um endereço");
        }
    }

    public void setSexo(Sexo sexo){
        this.sexo = Objects.requireNonNullElse(sexo, Sexo.NAO_INFORMADO);
    }

    public void setAltura(Integer altura){
        this.altura = altura;
        isAlturaValido();
    }

    private void isAlturaValido() {
        if (altura <= 0) {
            throw new ValidacaoDominioException("Altura inválida");
        }
    }

    public void setPeso(Double peso) {
        this.peso = peso;
        isPesoValido();
    }

    private void isPesoValido() {
        if (peso <= 0) {
            throw new ValidacaoDominioException("Peso inválido");
        }
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

    public Boolean isAtivo() {
        return ativo;
    }
}
