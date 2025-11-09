CREATE TABLE medicoes_estresse (
                                   medicao_id BIGINT PRIMARY KEY,
                                   variacao_frequencia_cardiaca DOUBLE PRECISION,
                                   condutividade_pele DOUBLE PRECISION,
                                   CONSTRAINT fk_estresse_medicao
                                       FOREIGN KEY (medicao_id)
                                           REFERENCES medicoes(id)
                                           ON DELETE CASCADE
);
