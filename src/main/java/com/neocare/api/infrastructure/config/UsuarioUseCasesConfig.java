package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.usuario.*;

import com.neocare.api.domain.repository.RoleRepository;
import com.neocare.api.domain.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioUseCasesConfig {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioUseCasesConfig(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase() {
        return new CriarUsuarioUseCaseImpl(localizarUsuarioUseCase(), usuarioRepository, roleRepository);
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

    @Bean
    public DesativarUsuarioUseCase desativarUsuarioUseCase() {
        return new DesativarUsuarioUseCaseImpl(usuarioRepository, localizarUsuarioUseCase());
    }

    @Bean
    public LocalizarUsuarioPorIdUseCase localizarUsuarioPorIdUseCase() {
        return new LocalizarUsuarioPorIdUseCaseImpl(usuarioRepository);
    }
}
