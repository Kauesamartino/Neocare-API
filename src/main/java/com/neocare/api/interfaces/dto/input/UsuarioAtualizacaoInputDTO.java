package com.neocare.api.interfaces.dto.input;

import com.neocare.api.domain.enums.Sexo;

import java.time.LocalDate;

/**
 * Dados de entrada para atualização do usuário
 */
public record UsuarioAtualizacaoInputDTO(
        String nome,
        String sobrenome,
        String cpf,
        String telefone,
        String email,
        LocalDate dataNascimento,
        Sexo sexo,
        Integer altura,
        Double peso,
        EnderecoInputDTO endereco
) {
}
