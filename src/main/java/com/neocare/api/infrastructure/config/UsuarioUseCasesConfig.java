package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.usuario.CriarUsuarioUseCaseImpl;
import com.neocare.api.application.usecase.usuario.EditarUsuarioUseCaseImpl;
import com.neocare.api.application.usecase.usuario.LocalizarUsuarioUseCaseImpl;
import com.neocare.api.application.usecase.usuario.LocalizarTodosOsUsuariosUseCaseImpl;

import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.application.usecase.usuario.CriarUsuarioUseCase;
import com.neocare.api.application.usecase.usuario.EditarUsuarioUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarTodosOsUsuariosUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioUseCasesConfig {

    private final UsuarioRepository usuarioRepository;

    public UsuarioUseCasesConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase() {
        return new CriarUsuarioUseCaseImpl(localizarUsuarioUseCase(), usuarioRepository);
    }

    @Bean
    public LocalizarUsuarioUseCase localizarUsuarioUseCase() {
        return new LocalizarUsuarioUseCaseImpl(usuarioRepository);
    }

    @Bean
    public LocalizarTodosOsUsuariosUseCase localizarTodosOsUsuariosUseCase() {
        return new LocalizarTodosOsUsuariosUseCaseImpl(usuarioRepository);
    }

    @Bean
    public EditarUsuarioUseCase editarUsuarioUseCase() {
        return new EditarUsuarioUseCaseImpl(localizarUsuarioUseCase(), usuarioRepository);
    }
}
