package com.braincare.api.domain.model;

import java.time.LocalDateTime;

public class Alerta {

    private Long id;

    private Long usuarioId;

    private Long medicaoId;

    private TipoAlerta tipoAlerta;

    private String valorDetectado;

    private Severidade severidade;

    private String mensagem;

    private LocalDateTime dataNotificacao;

    public enum TipoAlerta {
        AVC,
        ESTRESSE
    }

    public enum Severidade {
        BAIXA,
        MODERADA,
        ALTA
    }

}
