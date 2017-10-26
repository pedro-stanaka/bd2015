# BD 2015 #


Exemplo de cadastro de usuários desenvolvido durante o estágio de docência na graduação.

- **Instituição:** Universidade Estadual de Londrina
- **Curso:** Ciência da Computação
- **Disciplina:** Bancos de Dados A



## Ambiente de desenvolvimento ##


- **Plataforma:** Java EE 7
- **IDE:** IntelliJ IDEA 14 (ou NetBeans 8)
- **SGBD:** PostgreSQL 9.3



## Bibliotecas utilizadas ##


- [JSTL][1]
- [JDBC][2]
- [jQuery][3]
- [Bootstrap][4]
- [Gson][5]
- [Datepicker - jQuery Plugin][6]

[1]: https://jstl.java.net/
[2]: http://jdbc.postgresql.org/
[3]: http://jquery.com/
[4]: http://getbootstrap.com/
[5]: https://github.com/google/gson
[6]: https://github.com/eternicode/bootstrap-datepicker/



## Rodando o projeto ##


Definir as seguintes propriedades no arquivo `src/java/jdbc/datasource.properties`:

- **host:** endereço IP da máquina em que o serviço do PostgreSQL está executando
- **port:** porta em que o serviço do PostgreSQL está executando
- **name:** nome do banco de dados
- **user:** usuário de conexão ao banco de dados
- **password:** senha de conexão ao banco de dados

Criar a tabela `usuario` no banco de dados:

```sql
CREATE TABLE usuario
(
  id serial NOT NULL,
  login character varying(20) NOT NULL,
  senha character(32) NOT NULL,
  nome character varying(40) NOT NULL,
  nascimento date NOT NULL,
  avatar varchar(255),
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT uq_usuario_login UNIQUE (login)
);
```



### Créditos ###

Este projeto foi inicialmente desenvolvido por Paulo Henrique Oliveira [(@oliveiraph17)](https://github.com/oliveiraph17).
