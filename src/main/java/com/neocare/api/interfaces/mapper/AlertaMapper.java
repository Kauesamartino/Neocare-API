package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Alerta;
import com.neocare.api.infrastructure.entity.JpaAlertaEntity;
import com.neocare.api.infrastructure.entity.JpaMedicaoEntity;
import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;

public final class AlertaMapper {

    private AlertaMapper() {
    }

    public static JpaAlertaEntity toEntity(Alerta alerta, JpaUsuarioEntity usuarioEntity, JpaMedicaoEntity medicaoEntity) {
        return new JpaAlertaEntity(
                usuarioEntity,
                medicaoEntity,
                alerta.getTipoAlerta(),
                alerta.getValorDetectado(),
                alerta.getSeveridade(),
                alerta.getMensagem(),
                alerta.getDataNotificacao()
        );
    }

    public static Alerta toDomain(JpaAlertaEntity entity) {
        return new Alerta(
                entity.getId(),
                entity.getUsuarioEntity().getId(),
                entity.getMedicaoEntity().getId(),
                entity.getTipoAlerta(),
                entity.getValorDetectado(),
                entity.getSeveridade(),
                entity.getMensagem(),
                entity.getDataNotificacao()
        );
    }
}
