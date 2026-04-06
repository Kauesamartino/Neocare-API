package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCase;
import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCaseImpl;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicaoEstresseUseCasesConfig {

    private final MedicaoEstresseRepository medicaoEstresseRepository;
    private final AlertaRepository alertaRepository;

    public MedicaoEstresseUseCasesConfig(MedicaoEstresseRepository medicaoEstresseRepository, AlertaRepository alertaRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
        this.alertaRepository = alertaRepository;
    }

    @Bean
    public RegistrarMedicaoEstresseUseCase registrarMedicaoEstresseUseCase(){
        return new RegistrarMedicaoEstresseUseCaseImpl(medicaoEstresseRepository, alertaRepository);
    }

}
