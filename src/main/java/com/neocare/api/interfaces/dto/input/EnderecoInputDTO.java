package com.neocare.api.interfaces.dto.input;

public record EnderecoInputDTO(
        String logradouro,
        String bairro,
        String cep,
        String numero,
        String complemento,
        String cidade,
        String uf
) {
}
