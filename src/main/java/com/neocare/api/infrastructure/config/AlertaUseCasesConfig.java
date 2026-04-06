package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.alerta.ListarAlertasUseCase;
import com.neocare.api.application.usecase.alerta.ListarAlertasUseCaseImpl;
import com.neocare.api.domain.repository.AlertaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlertaUseCasesConfig {

    private final AlertaRepository alertaRepository;

    public AlertaUseCasesConfig(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Bean
    public ListarAlertasUseCase listarAlertasUseCase() {
        return new ListarAlertasUseCaseImpl(alertaRepository);
    }
}
