package com.neocare.api.interfaces.controller;

import com.neocare.api.application.usecase.dispositivo.LocalizarDispositivoUseCase;
import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarUsuarioPorIdUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarUsuarioUseCase;
import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;
import com.neocare.api.interfaces.mapper.MedicaoEstresseMapper;


public final class MedicaoControllerImpl implements  MedicaoController {

    private final RegistrarMedicaoEstresseUseCase registrarMedicaoEstresse;
    private final LocalizarDispositivoUseCase localizarDispositivoUseCase;
    private final LocalizarUsuarioPorIdUseCase localizarUsuarioPorIdUseCase;

    public MedicaoControllerImpl(RegistrarMedicaoEstresseUseCase registrarMedicaoEstresse, LocalizarDispositivoUseCase localizarDispositivoUseCase, LocalizarUsuarioPorIdUseCase localizarUsuarioPorIdUseCase) {
        this.registrarMedicaoEstresse = registrarMedicaoEstresse;
        this.localizarDispositivoUseCase = localizarDispositivoUseCase;
        this.localizarUsuarioPorIdUseCase = localizarUsuarioPorIdUseCase;
    }

    @Override
    public MedicaoEstresseOutDto registrarMedicaoEstresse(MedicaoEstresseInDto medicaoEstresseInDto) {
        MedicaoEstresse medicaoEstresse = MedicaoEstresseMapper.toModel(medicaoEstresseInDto);
        MedicaoEstresse registeredMedicaoEstresse = registrarMedicaoEstresse.execute(medicaoEstresse);
        Usuario usuario = localizarUsuarioPorIdUseCase.execute(registeredMedicaoEstresse.getIdUsuario());
        Dispositivo dispositivo = localizarDispositivoUseCase.execute(registeredMedicaoEstresse.getIdUsuario());
        return MedicaoEstresseMapper.toOutDto(registeredMedicaoEstresse, usuario.getNome(), dispositivo);
    }

}
