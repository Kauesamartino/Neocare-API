package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;

public class LocalizarUsuarioPorIdUseCaseImpl implements LocalizarUsuarioPorIdUseCase {

    private final UsuarioRepository usuarioRepository;

    public LocalizarUsuarioPorIdUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario execute(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }
}
