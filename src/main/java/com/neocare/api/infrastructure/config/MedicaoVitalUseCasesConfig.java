package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.medicao.vital.ListarMedicoesVitaisUseCase;
import com.neocare.api.application.usecase.medicao.vital.ListarMedicoesVitaisUseCaseImpl;
import com.neocare.api.application.usecase.medicao.vital.RegistrarMedicaoVitalUseCase;
import com.neocare.api.application.usecase.medicao.vital.RegistrarMedicaoVitalUseCaseImpl;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoVitalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicaoVitalUseCasesConfig {

    private final MedicaoVitalRepository medicaoVitalRepository;
    private final AlertaRepository alertaRepository;

    public MedicaoVitalUseCasesConfig(MedicaoVitalRepository medicaoVitalRepository, AlertaRepository alertaRepository) {
        this.medicaoVitalRepository = medicaoVitalRepository;
        this.alertaRepository = alertaRepository;
    }

    @Bean
    public RegistrarMedicaoVitalUseCase registrarMedicaoVitalUseCase() {
        return new RegistrarMedicaoVitalUseCaseImpl(medicaoVitalRepository, alertaRepository);
    }

    @Bean
    public ListarMedicoesVitaisUseCase listarMedicoesVitaisUseCase() {
        return new ListarMedicoesVitaisUseCaseImpl(medicaoVitalRepository);
    }
}
