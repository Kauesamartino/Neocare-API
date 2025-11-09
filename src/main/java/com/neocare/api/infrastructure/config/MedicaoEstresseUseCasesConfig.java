package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCase;
import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCaseImpl;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicaoEstresseUseCasesConfig {

    private final MedicaoEstresseRepository medicaoEstresseRepository;

    public MedicaoEstresseUseCasesConfig(MedicaoEstresseRepository medicaoEstresseRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
    }

    @Bean
    public RegistrarMedicaoEstresseUseCase registrarMedicaoEstresseUseCase(){
        return new RegistrarMedicaoEstresseUseCaseImpl(medicaoEstresseRepository);
    }

}
