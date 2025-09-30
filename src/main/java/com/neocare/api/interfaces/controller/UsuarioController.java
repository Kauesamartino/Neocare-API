package com.neocare.api.interfaces.controller;


import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;

/**
 * Interface pura para o controller de Cliente, seguindo os princípios da Clean Architecture.
 * Esta interface define as operações disponíveis sem acoplamento com frameworks externos.
 */
public interface UsuarioController {

    /**
     * Cria um novo usuario
     *
     * @param usuarioInputDTO dados do cliente a ser criado
     * @return dados do cliente criado
     */
    UsuarioOutputDTO criarUsuario(UsuarioInputDTO usuarioInputDTO);
}
