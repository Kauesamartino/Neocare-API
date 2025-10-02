package com.neocare.api.domain.usecase.usuario;

import com.neocare.api.domain.model.Usuario;

public interface LocalizarUsuarioUseCase {
    Usuario execute(String cpf);
}
