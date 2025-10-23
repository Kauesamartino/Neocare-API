package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.repository.UsuarioRepository;

public class DesativarUsuarioUseCaseImpl implements DesativarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public DesativarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public void execute(String cpf) {
        usuarioRepository.desativar(cpf);
    }
}
