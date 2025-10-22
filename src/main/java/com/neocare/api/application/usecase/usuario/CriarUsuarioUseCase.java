package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;

public interface CriarUsuarioUseCase {
    Usuario execute(Usuario usuario);
}
