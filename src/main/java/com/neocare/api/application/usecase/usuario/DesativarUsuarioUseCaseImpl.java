package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import jakarta.persistence.EntityNotFoundException;

public final class DesativarUsuarioUseCaseImpl implements DesativarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final LocalizarUsuarioUseCase localizarUsuarioUseCase;

    public DesativarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, LocalizarUsuarioUseCase localizarUsuarioUseCase) {
        this.usuarioRepository = usuarioRepository;
        this.localizarUsuarioUseCase = localizarUsuarioUseCase;
    }


    @Override
    public void execute(String cpf) {

        if (localizarUsuarioUseCase.execute(cpf) == null) {
            throw new EntityNotFoundException("Usuário não encontrado para desativação");
        }
        usuarioRepository.desativar(cpf);
    }
}
