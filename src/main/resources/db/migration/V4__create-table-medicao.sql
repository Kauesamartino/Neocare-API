CREATE TABLE medicoes (
                          id BIGSERIAL PRIMARY KEY,
                          usuario_id BIGINT NOT NULL,
                          dispositivo_id BIGINT NOT NULL,
                          data_medicao TIMESTAMP NOT NULL,
                          tipo_medicao VARCHAR(50) NOT NULL,
                          CONSTRAINT fk_medicoes_usuario
                              FOREIGN KEY (usuario_id)
                                  REFERENCES usuario(id)
                                  ON DELETE CASCADE,
                          CONSTRAINT fk_medicoes_dispositivo
                              FOREIGN KEY (dispositivo_id)
                                  REFERENCES dispositivo(id)
                                  ON DELETE CASCADE
);


