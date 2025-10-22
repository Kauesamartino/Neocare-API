package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.usuario.CriarUsuarioUseCase;
import com.neocare.api.application.usecase.usuario.EditarUsuarioUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarTodosOsUsuariosUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarUsuarioUseCase;
import com.neocare.api.interfaces.controller.UsuarioController;
import com.neocare.api.interfaces.controller.UsuarioControllerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    public UsuarioController usuarioController(
            CriarUsuarioUseCase criarUsuarioUseCase,
            LocalizarUsuarioUseCase localizarUsuarioUseCase,
            LocalizarTodosOsUsuariosUseCase localizarTodosOsUsuariosUseCase,
            EditarUsuarioUseCase editarUsuarioUseCase
    ){
        return new UsuarioControllerImpl(
                criarUsuarioUseCase,
                localizarUsuarioUseCase,
                localizarTodosOsUsuariosUseCase,
                editarUsuarioUseCase);
    }

}
