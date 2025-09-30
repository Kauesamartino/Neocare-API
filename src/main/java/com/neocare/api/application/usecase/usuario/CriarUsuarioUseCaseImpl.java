package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.domain.usecase.usuario.CriarUsuarioUseCase;
import com.neocare.api.domain.usecase.usuario.LocalizarUsuarioUseCase;

public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final LocalizarUsuarioUseCase localizarUsuarioUseCase;

    public CriarUsuarioUseCaseImpl(LocalizarUsuarioUseCase localizarUsuarioUseCase, UsuarioRepository usuarioRepository) {
        this.localizarUsuarioUseCase = localizarUsuarioUseCase;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        return null;
    }
}
