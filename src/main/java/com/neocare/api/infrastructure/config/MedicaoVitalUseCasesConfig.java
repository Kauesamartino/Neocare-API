package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.medicao.vital.RegistrarMedicaoVitalUseCase;
import com.neocare.api.application.usecase.medicao.vital.RegistrarMedicaoVitalUseCaseImpl;
import com.neocare.api.domain.repository.MedicaoVitalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicaoVitalUseCasesConfig {

    private final MedicaoVitalRepository medicaoVitalRepository;

    public MedicaoVitalUseCasesConfig(MedicaoVitalRepository medicaoVitalRepository) {
        this.medicaoVitalRepository = medicaoVitalRepository;
    }

    @Bean
    public RegistrarMedicaoVitalUseCase registrarMedicaoVitalUseCase() {
        return new RegistrarMedicaoVitalUseCaseImpl(medicaoVitalRepository);
    }
}
