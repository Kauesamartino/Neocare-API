package com.neocare.api.infrastructure.config;

import com.neocare.api.application.usecase.medicao.estresse.ListarMedicoesEstresseUseCase;
import com.neocare.api.application.usecase.medicao.estresse.ListarMedicoesEstresseUseCaseImpl;
import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCase;
import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCaseImpl;
import com.neocare.api.application.usecase.medicao.metrica.CalcularMetricaEstresseUseCase;
import com.neocare.api.application.usecase.medicao.metrica.CalcularMetricaEstresseUseCaseImpl;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
import com.neocare.api.domain.repository.MetricaEstresseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicaoEstresseUseCasesConfig {

    private final MedicaoEstresseRepository medicaoEstresseRepository;
    private final AlertaRepository alertaRepository;
    private final MetricaEstresseRepository metricaEstresseRepository;

    public MedicaoEstresseUseCasesConfig(MedicaoEstresseRepository medicaoEstresseRepository,
                                         AlertaRepository alertaRepository,
                                         MetricaEstresseRepository metricaEstresseRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
        this.alertaRepository = alertaRepository;
        this.metricaEstresseRepository = metricaEstresseRepository;
    }

    @Bean
    public RegistrarMedicaoEstresseUseCase registrarMedicaoEstresseUseCase(){
        return new RegistrarMedicaoEstresseUseCaseImpl(medicaoEstresseRepository, alertaRepository, metricaEstresseRepository);
    }

    @Bean
    public ListarMedicoesEstresseUseCase listarMedicoesEstresseUseCase() {
        return new ListarMedicoesEstresseUseCaseImpl(medicaoEstresseRepository);
    }

    @Bean
    public CalcularMetricaEstresseUseCase calcularMetricaEstresseUseCase() {
        return new CalcularMetricaEstresseUseCaseImpl(metricaEstresseRepository);
    }
}
