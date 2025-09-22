# Global Report #

## Introdução

A API Rest escolhida pelo grupo permite "simular" uma clinica Veterinária e pode ser encontrada na Pasta "project". A mesma possui mais de 5 agregados/controladores, sendo que cada membro será responsável por analisar, documentar e testar um deles.
A tecnologia usada pela API é o Spring Boot, sendo que requer uma versão igual ou superior ao JAVA SDK 17 e utiliza com Base de Dados Postgres.

Na pasta do projeto está presente um docker-compose de forma a correr a API (e BD) no docker de forma a facilitar todo o trabalho do grupo.
Pode ser corrido com o seguinte comando (depois de ter o docker a correr):
```sh
docker run -p 9966:9966 springcommunity/spring-petclinic-rest
```

Para testar um endpoint ou apenas consultar os mesmos podemos aceder:
* [http://localhost:9966/petclinic/](http://localhost:9966/petclinic/)

Nota: Inicialmente cada endpoint tinha autorização especifica por "roles" e optamos por retirar de forma a facilitar todo o trabalho. (ex.: Apenas os admins podiam fazer um GET de Pets)
## Modelo Dominio

![md.jpg](md.jpg)

## GQM

![GQM.png](GQM.png)

## Distribuição de Agregados

Este capítulo indica os agregados de cada aluno:

- [1181180](./1181180/README.md):
Ficou responsável pelos pedidos da classe OwnersController, que para alem das operações CRUD, possui 3 outros endpoints mais complexos e especificos.

-  [1191526](./1191526/README.md):
Ficou responsável pelos pedidos da classe VetController, que possui apenas as operações CRUD.

-  [1240485](./1240485/README.md):
Ficou responsável pelos pedidos da classe PetTypesController, que possui apenas as operações CRUD.

-  [1201100](./1201100/README.md):
   Ficou responsável pelos pedidos da classe SpecialityController, que possui apenas as operações CRUD.