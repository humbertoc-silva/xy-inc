# - xy-inc -
Plataforma que fornece toda a inteligêincia ao novo receptor GPS produzido pela empresa XY Inc.

## Tecnologias adotadas no desenvolvimento da aplicação.

* Java (JDK 1.8.0_131)
* Apache Maven 3.5.0 (build e gerenciamento de dependências)
* Apache Tomcat (servlet container)
* Spring Boot 1.5.4
* MySQL 5.7
* H2 Database Engine (testes de integração)

Instruções para instalação do Apache Maven e configuração da veriável de ambiente `JAVA_HOME` podem ser encontradas [aqui](https://maven.apache.org/install.html).

## Instruções para execução e testes da aplicação

O computador utilizado nos testes deve possuir o banco de dados MySQL 5.7 devidademente instalado e configurado. A aplicação utiliza o 
usuário `root` e a senha `admin` para se conectar ao banco de dados. Em seguida é necessário criar o schema `xy_inc` utilizando o usuário
`root` executando o comando abaixo:

```sql
CREATE SCHEMA `xy_inc` DEFAULT CHARACTER SET utf8;
```
Após a criação do schema `xy_inc` o projeto poderá ser contruído. Acesse o diretório raiz do projeto, onde o arquivo `pom.xml` se encontra,
utilizando um terminal e execute o comando abaixo:

```shell
mvn clean package
```

Neste momento o download das dependências do projeto será feito, os testes de unidade e integração serão executados e o projeto será construído.
Após a construção do projeto, acesse o diretório `target` utilizando um terminal e execute o comando abaixo:

```shell
java -jar poi-1.0.0.jar
```

Este comando irá iniciar o servlet container e a aplicação.

### Teste da API via cliente HTTP

1. Serviço de cadastro de pontos de interesse:
* **URL:** http://localhost:8080/api/poi
* **Método HTTP:** POST
* **Header:** Content-Type – application/json;charset=UTF-8
* **Body:** {"nome": "Taxi", "x": 13, "y": 11}

2. Serviço para listar todos os pontos de interesse:
* **URL:** http://localhost:8080/api/poi
* **Método HTTP:** GET

3. Serviço para listar os pontos de interesse por proximidade:
* **URL:** http://localhost:8080/api/poi/proximidade?x=20&y=10&dMax=10
* **Método HTTP:** GET
