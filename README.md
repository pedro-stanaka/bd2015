BD 2014
=======

Exemplo inicial para o trabalho de Bancos de Dados A.



Ambiente de desenvolvimento
===========================

- **Plataforma:** Java EE 7
- **IDE:** NetBeans 8
- **SGBD:** PostgreSQL 9.3
- **Sistema operacional:** Ubuntu 14.04 LTS



Bibliotecas utilizadas
======================

- [JSTL][1]
- [JDBC - PostgreSQL][2]
- [jQuery][3]
- [Bootstrap][4]
- [Gson][5]

[1]: https://jstl.java.net/
[2]: http://jdbc.postgresql.org/
[3]: http://jquery.com/
[4]: http://getbootstrap.com/
[5]: https://code.google.com/p/google-gson/



Como executar
=============

Definir as seguintes propriedades no arquivo `src/java/jdbc/datasource.properties`:

- **host:** endereço IP da máquina em que o serviço do PostgreSQL está executando
- **port:** porta em que o serviço do PostgreSQL está executando
- **name:** nome do banco de dados
- **user:** usuário de conexão ao banco de dados
- **password:** senha de conexão ao banco de dados

Criar a seguinte tabela no banco de dados:

```SQL
CREATE TABLE usuario
(
  id serial NOT NULL,
  login character varying(20) NOT NULL,
  senha character(32) NOT NULL,
  nome character varying(40) NOT NULL,
  nascimento date NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT uq_usuario_login UNIQUE (login)
)
```
