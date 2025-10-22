package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;

public interface LocalizarUsuarioUseCase {
    Usuario execute(String cpf);
}
