CREATE TABLE alertas (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    medicao_id BIGINT NOT NULL,
    tipo_alerta VARCHAR(50) NOT NULL,
    valor_detectado VARCHAR(100),
    severidade VARCHAR(50) NOT NULL,
    mensagem VARCHAR(255),
    data_notificacao TIMESTAMP NOT NULL,
    CONSTRAINT fk_alertas_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuario(id)
            ON DELETE CASCADE,
    CONSTRAINT fk_alertas_medicao
        FOREIGN KEY (medicao_id)
            REFERENCES medicoes(id)
            ON DELETE CASCADE
);