CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60),
    sobrenome VARCHAR(60),
    cpf VARCHAR(14),
    telefone VARCHAR(20),
    data_nasc DATE,
    endereco_id INT,
    email VARCHAR(100),
    quarto_id INT,
    CONSTRAINT fk_cliente_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    CONSTRAINT fk_cliente_quarto FOREIGN KEY (quarto_id) REFERENCES quarto(id)
);
