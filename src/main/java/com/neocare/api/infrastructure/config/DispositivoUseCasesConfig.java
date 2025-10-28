package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.dispositivo.LocalizarDispositivoUseCase;
import com.neocare.api.application.usecase.dispositivo.LocalizarDispositivoUseCaseImpl;
import com.neocare.api.domain.repository.DispositivoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DispositivoUseCasesConfig {

    private final DispositivoRepository dispositivoRepository;

    public DispositivoUseCasesConfig(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    @Bean
    public LocalizarDispositivoUseCase localizarDispositivoUseCase() {
        return new LocalizarDispositivoUseCaseImpl(dispositivoRepository);
    }
}
