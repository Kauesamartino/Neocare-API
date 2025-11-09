CREATE TABLE dispositivo (
                             id BIGSERIAL PRIMARY KEY,
                             usuario_id BIGINT NOT NULL,
                             tipo_dispositivo VARCHAR(50) NOT NULL,
                             endereco_disp VARCHAR(100),
                             ativo BOOLEAN NOT NULL DEFAULT TRUE,
                             CONSTRAINT fk_dispositivo_usuario
                                 FOREIGN KEY (usuario_id)
                                     REFERENCES usuario(id)
                                     ON DELETE CASCADE

);
