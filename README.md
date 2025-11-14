
# 📚 Sistema de Gerenciamento de Biblioteca (Java + JDBC)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Pattern](https://img.shields.io/badge/Pattern-DAO-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)

Este é um projeto de console em Java que demonstra a implementação do padrão **DAO (Data Access Object)** para gerenciar usuários de uma biblioteca. [cite_start]O sistema se conecta a um banco de dados **MySQL** [cite: 1] e realiza operações **CRUD** (Create, Read, Update) básicas.

## ✨ Funcionalidades

O projeto é focado na camada de persistência de dados e inclui as seguintes operações:

* [cite_start]**Conexão com BD:** Gerencia a conexão com o MySQL usando uma `ConnectionFactory` dedicada[cite: 1].
* [cite_start]**Adicionar Usuário:** Insere novos usuários no banco de dados (`criaUsuario`)[cite: 3].
* [cite_start]**Listar Usuários:** Busca e retorna uma lista de todos os usuários cadastrados (`listarUsuarios`)[cite: 3].
* [cite_start]**Buscar por ID:** Retorna um usuário específico com base no seu ID (`buscarUsuarioPorId`)[cite: 3].
* [cite_start]**Atualizar Usuário:** Modifica os dados de um usuário existente no banco (`atualizaUsuario`)[cite: 3].

## 🛠️ Tecnologias Utilizadas

* **Java:** Linguagem principal do projeto.
* **JDBC (Java Database Connectivity):** API padrão do Java para conexão com bancos de dados.
* [cite_start]**MySQL:** Sistema de gerenciamento de banco de dados relacional[cite: 1].
* [cite_start]**Padrão DAO:** Utilizado para separar as regras de negócio da lógica de persistência de dados[cite: 3].

## 📂 Estrutura do Projeto

O projeto está dividido nas seguintes classes principais:

* `Usuario.java`: Classe de modelo (POJO) que representa a entidade "Usuário", com seus getters e setters.
* [cite_start]`ConnectionFactory.java`: Classe utilitária responsável por estabelecer e retornar a conexão com o banco de dados MySQL[cite: 1].
* `UsuarioDAO.java`: O Data Access Object. [cite_start]Esta classe contém todo o código SQL e a lógica para interagir com a tabela de usuários (CRUD)[cite: 3].
* [cite_start]`Biblioteca.java`: A classe principal (`main`) que serve como ponto de entrada para testar as funcionalidades do DAO (atualmente, testa a busca por ID)[cite: 2].

## ⚙️ Configuração Essencial

Para executar este projeto, você precisa configurar o ambiente local.

### 1. Banco de Dados

[cite_start]É necessário ter um servidor MySQL rodando localmente (`localhost:3306`)[cite: 1].

Execute o seguinte script SQL para criar o banco de dados e a tabela necessários:

```sql
/* 1. Cria o banco de dados */
CREATE DATABASE db_biblioteca;

/* 2. Seleciona o banco de dados */
USE db_biblioteca;

/* 3. Cria a tabela (use 'tb_usuarios' para consistência) */
CREATE TABLE tb_usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefone VARCHAR(20),
    tipo_usuario VARCHAR(50)
);
````

### 2\. Credenciais

[cite\_start]A conexão está configurada para usar as seguintes credenciais em `ConnectionFactory.java`[cite: 1]:

  * **Usuário:** `root`
  * **Senha:** `""` (vazia)

Ajuste esses valores se a sua configuração do MySQL for diferente.

### 3\. Driver JDBC

Este projeto requer o **MySQL Connector/J**. Você precisa baixar o arquivo `.jar` (driver JDBC) e adicioná-lo ao *build path* (caminho de compilação) do seu projeto na sua IDE (Eclipse, NetBeans, IntelliJ, etc.).

## ‼️ Pontos de Atenção (Bugs no Código)

Durante a configuração, observe estes dois problemas presentes nos arquivos de origem que precisam ser corrigidos para o projeto funcionar 100%:

1.  **Typo na URL de Conexão:**

      * [cite\_start]**Arquivo:** `ConnectionFactory.java` [cite: 1]
      * [cite\_start]**Problema:** A URL está escrita como `jbdc:mysql...`[cite: 1].
      * **Correção:** Altere para `jdbc:mysql...`

2.  **Inconsistência no Nome da Tabela:**

      * [cite\_start]**Arquivo:** `UsuarioDAO.java` [cite: 3]
      * [cite\_start]**Problema:** O método `criaUsuario` usa a tabela `tb_usuarios` [cite: 3][cite\_start], enquanto os métodos `listarUsuarios`, `buscarUsuarioPorId` e `atualizaUsuario` usam a tabela `usuarios`[cite: 3].
      * **Correção:** Padronize todos os métodos para usar o mesmo nome de tabela (o script SQL acima sugere `tb_usuarios`).

## 🚀 Como Executar

1.  Configure o banco de dados (Passo 1).
2.  Adicione o Driver JDBC ao seu projeto (Passo 3).
3.  Corrija os "Pontos de Atenção" (acima) nos arquivos `ConnectionFactory.java` e `UsuarioDAO.java`.
4.  Compile todos os arquivos `.java`.
5.  [cite\_start]Execute a classe `Biblioteca.java`[cite: 2].

[cite\_start]O `main` atual tentará buscar o usuário com `id = 7` [cite: 2] e imprimir seus dados no console.

```
```
