CREATE TABLE metricas_estresse (
    id BIGSERIAL PRIMARY KEY,
    medicao_estresse_id BIGINT NOT NULL UNIQUE,
    indice_estresse INTEGER NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    data_metrica TIMESTAMP NOT NULL,
    CONSTRAINT fk_metricas_medicao_estresse
        FOREIGN KEY (medicao_estresse_id)
            REFERENCES medicoes(id)
            ON DELETE CASCADE
);
