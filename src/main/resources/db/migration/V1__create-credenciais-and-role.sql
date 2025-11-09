CREATE TABLE role (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(50) UNIQUE NOT NULL,
                         description VARCHAR(200)
);

CREATE TABLE credenciais (
                         id BIGSERIAL PRIMARY KEY,
                         username VARCHAR(50) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         full_name VARCHAR(150) NOT NULL,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP
);

CREATE TABLE credenciais_role (
                         credenciais_id BIGINT NOT NULL,
                         role_id BIGINT NOT NULL,
                         PRIMARY KEY (credenciais_id, role_id),
                         CONSTRAINT fk_credenciais_role_credenciais
                         FOREIGN KEY (credenciais_id) REFERENCES credenciais(id) ON DELETE CASCADE,
                         CONSTRAINT fk_credenciais_role_role
                         FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

insert into role (name, description) values ('ROLE_ADMIN', 'Administrador do sistema com todas as permissões');
insert into role (name, description) values ('ROLE_USER', 'Usuário padrão com permissões limitadas');