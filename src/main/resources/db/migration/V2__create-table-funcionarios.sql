CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255),
    cpf CHAR(11) NOT NULL UNIQUE,
    salario DECIMAL,
    login VARCHAR(255) UNIQUE,
    senha VARCHAR(255)
);