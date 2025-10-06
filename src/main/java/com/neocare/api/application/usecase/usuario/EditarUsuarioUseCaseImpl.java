package com.neocare.api.application.usecase.usuario;

import com.neocare.api.application.exception.EntidadeNaoEncontradaException;
import com.neocare.api.application.exception.UsuarioUnsupportedOperation;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.domain.usecase.usuario.EditarUsuarioUseCase;
import com.neocare.api.domain.usecase.usuario.LocalizarUsuarioUseCase;

public final class EditarUsuarioUseCaseImpl implements EditarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final LocalizarUsuarioUseCase localizarUsuarioUseCase;

    public EditarUsuarioUseCaseImpl(LocalizarUsuarioUseCase localizarUsuarioUseCase, UsuarioRepository usuarioRepository) {
        this.localizarUsuarioUseCase = localizarUsuarioUseCase;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        try {
            localizarUsuarioUseCase.execute(usuario.getCpf());
        } catch (EntidadeNaoEncontradaException e) {
            throw new UsuarioUnsupportedOperation("Cliente não encontrado");
        }
        return usuarioRepository.update(usuario);
    }
}
