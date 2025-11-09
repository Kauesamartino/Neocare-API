CREATE TABLE medicoes (
                          id BIGSERIAL PRIMARY KEY,
                          id_usuario BIGINT NOT NULL,
                          id_dispositivo BIGINT NOT NULL,
                          data_medicao TIMESTAMP NOT NULL,
                          tipo_medicao VARCHAR(50) NOT NULL,
                          CONSTRAINT fk_medicoes_usuario
                              FOREIGN KEY (id_usuario)
                                  REFERENCES usuario(id)
                                  ON DELETE CASCADE,
                          CONSTRAINT fk_medicoes_dispositivo
                              FOREIGN KEY (id_dispositivo)
                                  REFERENCES dispositivo(id)
                                  ON DELETE CASCADE
);


