package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.domain.usecase.usuario.LocalizarTodosOsUsuariosUseCase;

import java.util.List;

public final class LocalizarTodosOsUsuariosUseCaseImpl implements LocalizarTodosOsUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    public LocalizarTodosOsUsuariosUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Usuario> execute() {
        return usuarioRepository.findAllByAtivoTrue();
    }
}
