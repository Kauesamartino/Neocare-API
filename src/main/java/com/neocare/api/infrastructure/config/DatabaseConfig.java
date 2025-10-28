package com.neocare.api.infrastructure.config;

import com.neocare.api.domain.repository.DispositivoRepository;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.infrastructure.persistance.DispositivoRepositoryAdapter;
import com.neocare.api.infrastructure.persistance.UsuarioRepositoryAdapter;
import com.neocare.api.infrastructure.repository.JpaDispositivoRepository;
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

    @Bean
    public DispositivoRepository dispositivoRepository(JpaDispositivoRepository jpaDispositivoRepository) {
        final Logger logger = LoggerFactory.getLogger(DispositivoRepositoryAdapter.class);
        return new DispositivoRepositoryAdapter(jpaDispositivoRepository, logger);
    }

}
