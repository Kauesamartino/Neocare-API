package com.neocare.api.interfaces.dto.input;

import com.neocare.api.domain.enums.Sexo;

import java.time.LocalDate;

public record UsuarioInputDTO(
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento,
        Sexo sexo,
        Integer altura,
        Double peso,
        EnderecoInputDTO endereco
) {
}
