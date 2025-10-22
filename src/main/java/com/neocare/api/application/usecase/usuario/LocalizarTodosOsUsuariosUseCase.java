package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Usuario;

import java.util.List;

public interface LocalizarTodosOsUsuariosUseCase {
    List<Usuario> execute();
}
