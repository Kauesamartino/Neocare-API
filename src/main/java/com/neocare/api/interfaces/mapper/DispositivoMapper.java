package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.infrastructure.entity.JpaDispositivoEntity;

public final class DispositivoMapper {

    private DispositivoMapper() {
    }

    public static Dispositivo jpaEntityToDomain(JpaDispositivoEntity entity) {
        Dispositivo dispositivo = new Dispositivo(
                entity.getUsuarioEntity().getId(),
                entity.getTipoDispositivo(),
                entity.getEnderecoDisp(),
                entity.getAtivo()
        );
        dispositivo.setId(entity.getId());
        return dispositivo;
    }
}
