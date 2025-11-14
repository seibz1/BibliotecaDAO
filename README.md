📚 CRUD de Usuários para Biblioteca (Java + DAO)
Este é um projeto de console em Java que demonstra a implementação do padrão DAO (Data Access Object) para conectar a um banco de dados MySQL e gerenciar usuários de uma biblioteca.

🏛️ Arquitetura do Projeto
O código é estruturado para separar as responsabilidades, facilitando a manutenção:

Usuario.java (Modelo): Classe POJO que representa a entidade "Usuário", contendo apenas getters e setters para os dados.

ConnectionFactory.java (Conexão): Classe utilitária responsável por criar e retornar uma conexão com o banco de dados MySQL.

UsuarioDAO.java (DAO): A camada de acesso aos dados. Esta classe contém todo o código SQL (CRUD) para manipular os dados dos usuários no banco.

Biblioteca.java (Principal): Classe executável (main) que simula a camada de aplicação/negócio, utilizando o DAO para realizar operações.

⚙️ Funcionalidades (Operações CRUD)
A classe UsuarioDAO implementa as seguintes operações:

criaUsuario(Usuario usuario): Insere um novo usuário na tabela tb_usuarios.

listarUsuarios(): Retorna uma List<Usuario> com todos os registros da tabela usuarios.

buscarUsuarioPorId(int id): Busca e retorna um objeto Usuario específico pelo seu ID.

atualizaUsuario(Usuario usuario): Atualiza as informações de um usuário existente na tabela usuarios.

🚀 Como Configurar e Executar
Siga estes passos para rodar o projeto localmente:

1. Banco de Dados (MySQL)
Você precisa ter um servidor MySQL rodando (localhost:3306). Execute o script SQL abaixo para criar o banco de dados e a tabela:

SQL

/* 1. Crie o banco de dados */
CREATE DATABASE db_biblioteca;

/* 2. Use o banco de dados */
USE db_biblioteca;

/* 3. Crie a tabela (IMPORTANTE: veja a nota sobre 'tb_usuarios' abaixo) */
CREATE TABLE tb_usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefone VARCHAR(20),
    tipo_usuario VARCHAR(50)
);

/* 4. (Opcional) Insira um usuário para testar a busca */
INSERT INTO tb_usuarios (nome, email, telefone, tipo_usuario)
VALUES ('Ana Silva', 'ana.silva@email.com', '11987654321', 'Aluno');
2. Driver JDBC
Este projeto requer o MySQL Connector/J. Você deve baixar o arquivo .jar do driver e adicioná-lo ao Build Path (Caminho de Compilação) da sua IDE (Eclipse, NetBeans, IntelliJ, etc.).

3. Execução
Execute o arquivo Biblioteca.java. O método main atual está configurado para tentar buscar o usuário com id = 7.

‼️ Correções Necessárias no Código
Para o projeto funcionar corretamente, duas correções são necessárias nos arquivos de origem:

Typo na Conexão (ConnectionFactory.java):

A URL da conexão está escrita como jbdc:mysql....

Correção: Mude para jdbc:mysql....

Inconsistência na Tabela (UsuarioDAO.java):

O método criaUsuario salva dados na tabela tb_usuarios.

Os métodos listarUsuarios, buscarUsuarioPorId e atualizaUsuario tentam ler da tabela usuarios.

Correção: Padronize todos os métodos para usar o mesmo nome de tabela (ex: tb_usuarios, conforme o script SQL acima).
