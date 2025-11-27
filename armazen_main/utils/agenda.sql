create database armazen;
use armazen;

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2),
    quantidade INT DEFAULT 0
);

CREATE TABLE Movimentacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT,
    data_movimentacao VARCHAR(50),
    quantidade INT,
    tipo VARCHAR(50),
    FOREIGN KEY (produto_id) REFERENCES Produto(id)
);
