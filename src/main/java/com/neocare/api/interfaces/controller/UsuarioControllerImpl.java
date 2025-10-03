package com.neocare.api.interfaces.controller;

import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.usecase.usuario.CriarUsuarioUseCase;
import com.neocare.api.domain.usecase.usuario.LocalizarUsuarioUseCase;
import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioResumoOutputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;
import com.neocare.api.interfaces.mapper.UsuarioMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do controller de Usuario sem acoplamento com frameworks externos.
 * Segue os princípios da Clean Architecture, atuando como um adaptador entre os casos de uso e a interface externa.
 */
public final class UsuarioControllerImpl implements UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final LocalizarUsuarioUseCase localizarUsuarioUseCase;

    public UsuarioControllerImpl(CriarUsuarioUseCase criarUsuarioUseCase, LocalizarUsuarioUseCase localizarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.localizarUsuarioUseCase = localizarUsuarioUseCase;
    }

    @Override
    public UsuarioOutputDTO criarUsuario(UsuarioInputDTO usuarioInputDTO) {
        Usuario usuario =  UsuarioMapper.toModel(usuarioInputDTO);
        Usuario usuarioCreated = criarUsuarioUseCase.execute(usuario);
        return UsuarioMapper.toOutputDTO(usuarioCreated);
    }

    @Override
    public List<UsuarioResumoOutputDTO> listarUsuarios() {
        List<Usuario> usuarios = localizarTodosOsUsuariosUseCase.execute();

        return usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .map(UsuarioMapper::toResumoOutputDTO)
                .collect(Collectors.toList());
    }
}
