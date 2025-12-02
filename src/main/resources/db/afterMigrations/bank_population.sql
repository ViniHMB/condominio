
INSERT INTO quarto (numero, andar, mensalidade)
VALUES
('101', 2, 180.00),
('102', 1, 200.00),
('103', 3, 250.00),
('104', 2, 300.00);

INSERT INTO endereco (numero, logradouro, bairro, cep, cidade, uf, complemento)
VALUES
('50', 'Tv. itaby', 'Vila batista', '86900-000', 'Jandaia do sul', 'PR', 'casa cinza'),
('123', 'Rua das Flores', 'Centro', '87010-020', 'Maringá', 'PR', 'apto 301'),
('88', 'Av. Brasil', 'Zona 01', '87020-030', 'Maringá', 'PR', 'casa amarela'),
('45', 'Rua Paraná', 'Jardim América', '87040-050', 'Maringá', 'PR', 'fundos');

INSERT INTO cliente (nome, sobrenome, cpf, telefone, data_nasc, endereco_id, email, quarto_id)
VALUES
('Vini', 'Henrique', '01234567890123', '999112233', '2000-05-14', 1, 'teste@gmail.com', 1),
('Mariana', 'Silva', '98765432100', '44987654321', '1995-07-14', 2, 'mariana.silva@gmail.com', 2),
('Carlos', 'Souza', '12312312399', '44991234567', '1989-03-22', 3, 'carlos.souza@yahoo.com', 3),
('Fernanda', 'Oliveira', '45678912309', '44998877665', '2001-11-02', 4, 'fernanda.oliveira@outlook.com', 4);
