package com.neocare.api.interfaces.controller;


import com.neocare.api.interfaces.dto.input.UsuarioAtualizacaoInputDTO;
import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioResumoOutputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;

import java.util.List;

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

    /**
     * Lista todos os usuários
     *
     * @return lista de usuários
     */
    List<UsuarioResumoOutputDTO> listarUsuarios();

    /**
     * Localizar um usuário por CPF
     *
     * @param cpf do usuário a ser buscado
     * @return dados do usuário
     */
    UsuarioOutputDTO localizarUsuarioPorCpf(String cpf);

    /**
     * Edita um usuário existente
     *
     * @param usuarioInputDTO dados do usuário a ser editado
     * @return dados do usuário editado
     */
    UsuarioOutputDTO editarUsuario(UsuarioAtualizacaoInputDTO usuarioInputDTO);
}
