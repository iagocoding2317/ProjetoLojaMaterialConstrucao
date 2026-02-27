CREATE TABLE Pessoa (
    cpf      VARCHAR(20)  PRIMARY KEY,
    nome     VARCHAR(200) NOT NULL,
    idade    INT,
    endereco VARCHAR(200)
);

CREATE TABLE Cliente (
    id_cliente     SERIAL        PRIMARY KEY,
    cpf            VARCHAR(20)   UNIQUE NOT NULL,
    limite_credito DECIMAL(10,2),
    FOREIGN KEY (cpf) REFERENCES Pessoa(cpf)
);

CREATE TABLE Funcionario (
    id_funcionario    SERIAL      PRIMARY KEY,
    cpf               VARCHAR(20) UNIQUE NOT NULL,
    salario           DECIMAL(10,2),
    indice_fidelidade INT,
    FOREIGN KEY (cpf) REFERENCES Pessoa(cpf)
);

CREATE TABLE Produto (
    id_produto SERIAL        PRIMARY KEY,
    nome       VARCHAR(300)  NOT NULL,
    preco      DECIMAL(10,2) NOT NULL
);

CREATE TABLE Tinta (
    id_produto      INT          PRIMARY KEY,
    cor             VARCHAR(40),
    tipo_acabamento VARCHAR(100),
    volume_litros   FLOAT,
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

CREATE TABLE Tijolo (
    id_produto         INT          PRIMARY KEY,
    quantidade_estoque INT,
    preco_custo        DECIMAL(10,2),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto) 
);

CREATE TABLE Cimento (
    id_produto         INT          PRIMARY KEY,
    peso_kg            INT,
    quantidade_estoque INT,
    marca              VARCHAR(100),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto) 
);

CREATE TABLE Fornecedora (
    cnpj VARCHAR(18)  PRIMARY KEY,
    nome VARCHAR(200) NOT NULL
);

CREATE TABLE Compra (
    id_compra  SERIAL PRIMARY KEY,
    id_cliente INT    NOT NULL,
    id_produto INT    NOT NULL,
    quantidade INT    NOT NULL DEFAULT 1,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto) 
);

SELECT p.nome, p.cpf, p.idade, p.endereco, c.id_cliente, c.limite_credito
FROM Cliente c JOIN Pessoa p ON c.cpf = p.cpf;

SELECT p.nome, p.cpf, f.id_funcionario, f.indice_fidelidade, f.salario
FROM Funcionario f JOIN Pessoa p ON f.cpf = p.cpf;

SELECT p.id_produto, p.nome, p.preco, t.cor, t.tipo_acabamento, t.volume_litros
FROM Produto p JOIN Tinta t ON p.id_produto = t.id_produto;

SELECT p.id_produto, p.nome, p.preco, tj.quantidade_estoque, tj.preco_custo
FROM Produto p JOIN Tijolo tj ON p.id_produto = tj.id_produto;

SELECT p.id_produto, p.nome, p.preco, c.peso_kg, c.quantidade_estoque, c.marca
FROM Produto p JOIN Cimento c ON p.id_produto = c.id_produto;

SELECT p.nome AS nomeCliente, pr.nome AS nomeProduto, pr.preco, co.quantidade
FROM Compra co
JOIN Cliente c  ON co.id_cliente = c.id_cliente
JOIN Pessoa p   ON c.cpf = p.cpf
JOIN Produto pr ON co.id_produto = pr.id_produto
ORDER BY p.nome;

SELECT pr.nome AS nomeProduto, pr.preco, co.quantidade
FROM Compra co JOIN Produto pr ON co.id_produto = pr.id_produto
WHERE co.id_cliente = 1;

SELECT * FROM Cliente;

SELECT * FROM Pessoa;