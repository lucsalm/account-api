# Account API

Este documento está disponível em [Inglês](README-en.md), porém também está disponível em [Português](README.md).

## Visão geral

Este projeto é minha implementação do desafio [Rinha de Backend](https://github.com/zanfranceschi/rinha-de-backend-2024-q1). Em essência, o desafio consiste na criação de uma API capaz de realizar operações transacionais de crédito e débito na conta de um cliente, além de permitir a consulta de extrato. O tema central do desafio é o controle de concorrência, por esse motivo, a implementação explora conceitos [ACID](https://www.ibm.com/docs/pt-br/cics-tx/11.1?topic=processing-acid-properties-transactions), fazendo uso de ferramentas de controle do Spring Boot como [Transactional](https://docs.spring.io/spring-data/jpa/reference/jpa/transactions.html) e 
[Lock](https://docs.spring.io/spring-data/jpa/reference/jpa/locking.html).

## Detalhes

<details open>
      <summary><b>Tecnologias</b></summary>


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-4169E1.svg?style=for-the-badge&logo=PostgreSQL&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white)

</details>


<details open>
      <summary><b>Arquitetura</b></summary> "

![Arquitetura](src/main/resources/static/img/arquitetura.png)
</details>



<details open>
      <summary><b>Dados Iniciais</b></summary>

 **Clientes**

 | id | limite   | saldo inicial |
 |----|----------|---------------|
 | 1  | 100000   | 0             |
 | 2  | 80000    | 0             |
 | 3  | 1000000  | 0             |
 | 4  | 10000000 | 0             |
 | 5  | 500000   | 0             |

</details>

<details open>
      <summary><b>Como Usar</b></summary>

1. Certifique-se de que o Docker esteja instalado em sua máquina.
2. Clone este repositório para o seu ambiente local.
3. Navegue até o diretório do projeto.
4. No terminal, execute o seguinte comando para construir e iniciar o contêiner Docker:
    - No Linux, execute:
        ```bash
        docker compose up
        ```

    - No Windows, execute:
        ```bash
        docker-compose up
        ```

5. Após a construção dos contêineres e a inicialização da aplicação, acesse [Scallar](http://localhost:8080/scallar.html) para visualizar sua documentação. Você deverá ver a seguinte tela


**Observações:**
- Certifique-se de que as portas `9999`, `8081`, `8082` e `5432` não estão sendo utilizadas por outra aplicação em seu sistema para evitar conflitos. Se necessário, você pode modificar o mapeamento de porta no arquivo [docker-compose.yaml](docker-compose.yaml).
</details>
