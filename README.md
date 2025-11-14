# 📚 Sistema de Gerenciamento de Usuários - Biblioteca

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

Um projeto Java de console simples para gerenciar usuários de uma biblioteca. Este projeto demonstra a implementação do padrão de design **DAO (Data Access Object)** para separar a lógica de negócios da lógica de acesso a dados, utilizando **JDBC** para conexão com um banco de dados **MySQL**.

## ✨ Funcionalidades Principais

O sistema permite realizar operações CRUD (Criar, Ler, Atualizar) básicas na tabela de usuários:

* **Criar:** Registrar um novo usuário no banco de dados.
* **Listar:** Exibir uma lista de todos os usuários cadastrados.
* **Buscar por ID:** Pesquisar e retornar um usuário específico pelo seu ID.
* **Atualizar:** Modificar as informações de um usuário existente.

## 🛠️ Tecnologias Utilizadas

* **Java:** Linguagem principal do projeto.
* **JDBC (Java Database Connectivity):** API para a conexão e execução de consultas no banco de dados.
* **MySQL:** Sistema de gerenciamento de banco de dados relacional.

## ⚙️ Configuração e Instalação

Siga os passos abaixo para executar o projeto localmente.

### 1. Pré-requisitos

* [Java JDK](https://www.oracle.com/java/technologies/downloads/) (versão 8 ou superior)
* [MySQL Server](https://dev.mysql.com/downloads/mysql/) (versão 8.0 ou superior)
* Uma IDE Java (ex: Eclipse, IntelliJ, VS Code)

### 2. Configuração do Banco de Dados

1.  Acesse seu servidor MySQL.
2.  Crie o banco de dados `db_biblioteca`:

    ```sql
    CREATE DATABASE db_biblioteca;
    ```

3.  Use o banco de dados recém-criado:

    ```sql
    USE db_biblioteca;
    ```

4.  Crie a tabela `tb_usuarios` (usada no método `criaUsuario`). *Nota: outros métodos nos arquivos usam a tabela `usuarios`. Para consistência, certifique-se de usar o mesmo nome de tabela em todos os métodos DAO.*

    ```sql
    CREATE TABLE tb_usuarios (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        email VARCHAR(255),
        telefone VARCHAR(20),
        tipo_usuario VARCHAR(50)
    );
    ```

### 3. Configuração da Conexão

1.  Verifique se os dados de conexão no arquivo `ConnectionFactory.java` correspondem à sua configuração local do MySQL:

    * **URL:** `jdbc:mysql://localhost:3306/db_biblioteca` (Note que o arquivo original contém um erro de digitação `jbdc`. O correto é `jdbc`.)
    * **Usuário:** `root`
    * **Senha:** `""` (vazio)

2.  Ajuste esses valores se o seu usuário ou senha do MySQL forem diferentes.

### 4. Dependências (Driver JDBC)

Este projeto requer o driver JDBC do MySQL.

1.  Baixe o **MySQL Connector/J** (arquivo `.jar`) no [site oficial do MySQL](https://dev.mysql.com/downloads/connector/j/).
2.  Adicione o arquivo `.jar` ao Build Path (caminho de compilação) do seu projeto na sua IDE.
    * **Eclipse:** Clique com o botão direito no projeto > `Build Path` > `Configure Build Path...` > `Libraries` > `Add External JARs...`.
    * **IntelliJ:** `File` > `Project Structure...` > `Modules` > `Dependencies` > `+` (sinal de mais) > `JARs or directories...`.

### 5. Executando o Projeto

Compile e execute a classe principal `Biblioteca.java` para testar a funcionalidade (atualmente, ela testa a busca de usuário por ID).

## 🗂️ Estrutura do Projeto

O projeto está organizado no pacote `biblioteca` com as seguintes classes:

* `Usuario.java`
    * **Modelo (POJO):** Classe que representa a entidade `Usuario`, contendo os atributos (id, nome, email, etc.) e seus métodos getters/setters.
* `ConnectionFactory.java`
    * **Fábrica de Conexões:** Classe responsável por estabelecer e retornar uma conexão com o banco de dados MySQL.
* `UsuarioDAO.java`
    * **DAO (Data Access Object):** Classe que centraliza toda a lógica de acesso a dados (SQL) para a entidade `Usuario`. Contém os métodos `criaUsuario`, `listarUsuarios`, `buscarUsuarioPorId` e `atualizaUsuario`.
* `Biblioteca.java`
    * **Classe Principal:** Contém o método `main` e serve como ponto de entrada para testar as funcionalidades do DAO.

## 🚀 Exemplo de Uso

Você pode modificar o `main` na classe `Biblioteca.java` para testar outras funcionalidades, como a criação de um usuário:

```java
package biblioteca;

import java.sql.SQLException;

public class Biblioteca {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // --- Exemplo: Criando um novo usuário ---
        try {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome("Ana Silva");
            novoUsuario.setEmail("ana.silva@email.com");
            novoUsuario.setTelefone("11987654321");
            novoUsuario.setTipo_usuario("Estudante");

            usuarioDAO.criaUsuario(novoUsuario);
            System.out.println("Usuário criado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }

        // --- Exemplo: Buscando um usuário (como no arquivo original) ---
        try {
            int idBuscado = 1; // Mude para o ID que deseja buscar
            Usuario usuario = usuarioDAO.buscarUsuarioPorId(idBuscado);

            if (usuario != null) {
                System.out.println("Usuário encontrado!!");
                System.out.println("ID: " + usuario.getId());
                System.out.println("Nome: " + usuario.getNome());
                // ... outros campos
            } else {
                System.out.println("Usuário com ID " + idBuscado + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }
}
