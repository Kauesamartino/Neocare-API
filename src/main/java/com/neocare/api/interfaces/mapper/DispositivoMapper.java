package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.infrastructure.entity.JpaDispositivoEntity;

public final class DispositivoMapper {

    public static Dispositivo jpaEntityToDomain(JpaDispositivoEntity entity) {
        return new Dispositivo(
                entity.getUsuarioEntity().getId(),
                entity.getTipoDispositivo(),
                entity.getEnderecoDisp(),
                entity.getAtivo()
        );
    }
}
