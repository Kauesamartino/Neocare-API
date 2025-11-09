CREATE TABLE medicoes_vitais (
                                 medicao_id BIGINT PRIMARY KEY,
                                 batimentos_por_minuto INTEGER,
                                 oxigenacao_sangue DOUBLE PRECISION,
                                 pressao_sistolica INTEGER,
                                 pressao_diastolica INTEGER,
                                 CONSTRAINT fk_vitais_medicao
                                     FOREIGN KEY (medicao_id)
                                         REFERENCES medicoes(id)
                                         ON DELETE CASCADE
);
