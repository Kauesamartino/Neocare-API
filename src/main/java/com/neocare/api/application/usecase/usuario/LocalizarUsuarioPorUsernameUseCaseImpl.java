package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;

public final class LocalizarUsuarioPorUsernameUseCaseImpl implements LocalizarUsuarioPorUsernameUseCase {

    private final UsuarioRepository usuarioRepository;

    public LocalizarUsuarioPorUsernameUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario localizarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
