CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),
    preco_venda DECIMAL NOT NULL,
    preco_compra DECIMAL,
    quantidade INTEGER NOT NULL
);