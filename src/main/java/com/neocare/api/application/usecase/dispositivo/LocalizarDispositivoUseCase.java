package com.neocare.api.application.usecase.dispositivo;

import com.neocare.api.domain.model.Dispositivo;

public interface LocalizarDispositivoUseCase {

    Dispositivo execute(Long id);

}
