CREATE TABLE endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(20),
    logradouro VARCHAR(100),
    bairro VARCHAR(60),
    cep VARCHAR(15),
    cidade VARCHAR(60),
    uf VARCHAR(2),
    complemento VARCHAR(100)
);
