#!/bin/bash
echo "========================================"
echo " Compilando Loja de Material..."
echo "========================================"

mkdir -p bin

javac -cp "lib/postgresql-42.7.10.jar" -d bin src/*.java

if [ $? -ne 0 ]; then
    echo "ERRO na compilacao!"
    exit 1
fi

echo "Compilado com sucesso!"
echo ""
echo "========================================"
echo " Iniciando sistema..."
echo "========================================"

java -cp "bin:lib/postgresql-42.7.10.jar" PrincipalLoja
