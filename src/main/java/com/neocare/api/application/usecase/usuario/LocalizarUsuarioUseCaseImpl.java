package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;

public final class LocalizarUsuarioUseCaseImpl implements LocalizarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public LocalizarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario execute(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}
