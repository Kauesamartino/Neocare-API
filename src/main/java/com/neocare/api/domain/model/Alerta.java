package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.Severidade;
import com.neocare.api.domain.enums.TipoAlerta;

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

}
