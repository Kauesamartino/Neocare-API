package com.neocare.api.infrastructure.entity;

import com.neocare.api.domain.enums.Severidade;
import com.neocare.api.domain.enums.TipoAlerta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "alertas")
@Entity
public class JpaAlertaEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private JpaUsuarioEntity usuarioEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicao_id", nullable = false)
    private JpaMedicaoEntity medicaoEntity;

    @Enumerated(EnumType.STRING)
    private TipoAlerta tipoAlerta;

    private String valorDetectado;

    @Enumerated(EnumType.STRING)
    private Severidade severidade;

    private String mensagem;

    private LocalDateTime dataNotificacao;

    public JpaAlertaEntity() {
    }

    public JpaAlertaEntity(JpaUsuarioEntity usuarioEntity, JpaMedicaoEntity medicaoEntity, TipoAlerta tipoAlerta, String valorDetectado, Severidade severidade, String mensagem, LocalDateTime dataNotificacao) {
        this.usuarioEntity = usuarioEntity;
        this.medicaoEntity = medicaoEntity;
        this.tipoAlerta = tipoAlerta;
        this.valorDetectado = valorDetectado;
        this.severidade = severidade;
        this.mensagem = mensagem;
        this.dataNotificacao = dataNotificacao;
    }
}
