package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.application.exception.EntidadeNaoEncontradaException;
import com.neocare.api.application.exception.UsuarioUnsupportedOperation;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;

public final class RegistrarMedicaoEstresseUseCaseImpl implements RegistrarMedicaoEstresseUseCase{

    private final MedicaoEstresseRepository medicaoEstresseRepository;
    private final LocalizarMedicaoEstresseUseCase localizarMedicaoEstresseUseCase;

    public RegistrarMedicaoEstresseUseCaseImpl(MedicaoEstresseRepository medicaoEstresseRepository, LocalizarMedicaoEstresseUseCase localizarMedicaoEstresseUseCase) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
        this.localizarMedicaoEstresseUseCase = localizarMedicaoEstresseUseCase;
    }

    @Override
    public MedicaoEstresse execute(MedicaoEstresse medicaoEstresse) {
        try{
            localizarMedicaoEstresseUseCase.execute(medicaoEstresse.getId());
        } catch (EntidadeNaoEncontradaException e){
            return medicaoEstresseRepository.save(medicaoEstresse);
        }
        throw new UsuarioUnsupportedOperation("Medição já cadastrada");
    }
}
