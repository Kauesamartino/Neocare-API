package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.usuario.*;
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
            EditarUsuarioUseCase editarUsuarioUseCase,
            DesativarUsuarioUseCase desativarUsuarioUseCase
    ){
        return new UsuarioControllerImpl(
                criarUsuarioUseCase,
                localizarUsuarioUseCase,
                localizarTodosOsUsuariosUseCase,
                editarUsuarioUseCase,
                desativarUsuarioUseCase);
    }

}
