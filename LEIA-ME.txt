========================================================
  LOJA DE MATERIAL DE CONSTRUÇÃO — Guia de Configuração
========================================================

ESTRUTURA DO PROJETO:
─────────────────────
LojaMaterial/
├── src/                  ← todos os arquivos .java
├── lib/
│   └── postgresql-42.7.10.jar   ← driver JDBC (já incluído)
├── bin/                  ← gerado na compilação (não mexa)
├── sql/
│   └── loja_material.sql ← script do banco de dados
├── rodar.bat             ← Windows: compila e roda
└── rodar.sh              ← Linux/Mac: compila e roda


PASSO 1 — INSTALAR O POSTGRESQL
────────────────────────────────
Baixe e instale em: https://www.postgresql.org/download/
Durante a instalação defina:
  • Usuário:  postgres
  • Senha:    postgres
  • Porta:    5432


PASSO 2 — CRIAR O BANCO DE DADOS
─────────────────────────────────
Abra o pgAdmin ou o terminal psql e execute:

  CREATE DATABASE "LojaMaterial";

Depois selecione o banco LojaMaterial e execute o arquivo:
  sql/loja_material.sql

Isso criará todas as tabelas (Pessoa, Cliente, Funcionario,
Produto, Tinta, Tijolo, Cimento, Fornecedora, Compra).


PASSO 3 — VERIFICAR A CONEXÃO JAVA
────────────────────────────────────
Abra src/ConnectionPostgreSQL.java e confirme:

  url      = "jdbc:postgresql://localhost:5432/LojaMaterial"
  user     = "postgres"
  password = "postgres"

Se sua senha for diferente, altere o campo password.


PASSO 4 — INSTALAR O JAVA (JDK)
────────────────────────────────
Precisa do JDK 11 ou superior.
Baixe em: https://adoptium.net/

Para verificar se já tem instalado, rode no terminal:
  java -version
  javac -version


PASSO 5 — COMPILAR E RODAR
───────────────────────────
Windows:
  Dê duplo clique em  rodar.bat
  — OU —
  Abra o terminal na pasta LojaMaterial e execute:
    rodar.bat

Linux/Mac:
  Abra o terminal na pasta LojaMaterial e execute:
    chmod +x rodar.sh
    ./rodar.sh


PASSO 6 — USAR O SISTEMA
──────────────────────────
O menu aparece no terminal:

  ===== MENU LOJA =====
  1 - Cadastrar Cliente      → salva em Pessoa + Cliente
  2 - Cadastrar Fornecedora  → salva em Fornecedora
  3 - Listar Clientes        → lê do banco e mostra
  4 - Listar Produtos        → lê Tinta, Tijolo e Cimento
  5 - Realizar Compra        → salva em Compra
  6 - Listar Compras         → mostra cliente + produto + preço
  0 - Sair

Tudo que você digitar no Java é salvo no PostgreSQL.
Para conferir, abra o pgAdmin e execute:
  SELECT * FROM Cliente;
  SELECT * FROM Compra;


SOLUÇÃO DE PROBLEMAS
─────────────────────
Erro "Connection refused":
  → PostgreSQL não está rodando. Inicie o serviço.

Erro "database does not exist":
  → Rode o PASSO 2 novamente.

Erro "password authentication failed":
  → Corrija a senha em ConnectionPostgreSQL.java

Erro "javac not found":
  → Instale o JDK (PASSO 4).
========================================================
