package com.neocare.api.infrastructure.config;

import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.infrastructure.persistance.UsuarioRepositoryAdapter;
import com.neocare.api.infrastructure.repository.JpaUsuarioRepository;
import com.neocare.api.domain.logging.Logger;
import com.neocare.api.infrastructure.logging.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public UsuarioRepository clienteRepository(JpaUsuarioRepository jpaUsuarioRepository) {
        final Logger logger = LoggerFactory.getLogger(UsuarioRepositoryAdapter.class);
        return new UsuarioRepositoryAdapter(jpaUsuarioRepository, logger);
    }

}
