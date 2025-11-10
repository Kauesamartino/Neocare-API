INSERT INTO credenciais (username, password, full_name, created_at, updated_at) VALUES
('joaosilva', 'Senha!123', 'João Silva', NOW(), null);

INSERT INTO credenciais_role (credenciais_id, role_id) VALUES
(1, 2);

INSERT INTO usuario (nome, sobrenome, email, cpf, telefone, data_nascimento, sexo, altura, peso, logradouro, bairro, cep, numero, complemento, cidade, uf, credenciais_id) VALUES
('João', 'Silva', 'joaosilva@gmail.com', '52857264844', '(11) 98765-4321', '1985-07-15', 'MASCULINO', 175, 80.5, 'Rua das Flores', 'Jardim Primavera', '01234-567', '123', 'Apto 45', 'São Paulo', 'SP', 1);

INSERT INTO dispositivo (usuario_id, tipo_dispositivo, endereco_disp) VALUES
(1, 'ESP32', 'A4:CF:12:45:AE:CC ou 24:0a:c4:00:01:10'),
(1, 'STM32', 'B8:27:EB:45:AE:DD ou DC:A6:32:00:01:11'),
(1, 'SENSOR_CARDIACO', '00:1A:7D:DA:71:13 ou 3C:5A:B4:00:01:12');