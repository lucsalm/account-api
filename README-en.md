# Account API

This document is available in [English](README-en.md), but it's also available in [Portuguese](README.md).

## Overview

This project is my implementation of the [Rinha de Backend](https://github.com/zanfranceschi/rinha-de-backend-2024-q1) challenge. In essence, the challenge consists of creating an API capable of performing credit and debit transactional operations on a customer's account, as well as enabling statement inquiry. The central theme of the challenge is concurrency control, for this reason, the implementation explores [ACID](https://www.ibm.com/docs/en/cics-tx/11.1?topic=processing-acid-properties-transactions) concepts, making use of Spring Boot control tools such as [Transactional](https://docs.spring.io/spring-data/jpa/reference/jpa/transactions.html) and [Lock](https://docs.spring.io/spring-data/jpa/reference/jpa/locking.html).

## Details

<details open>
      <summary><b>Stack</b></summary>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![Postgres](https://img.shields.io/badge/PostgreSQL-4169E1.svg?style=for-the-badge&logo=PostgreSQL&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white)

</details>


<details open>
      <summary><b>Architecture</b></summary>

![Arquitetura](src/main/resources/static/img/arquitetura.png)
</details>



<details open>
      <summary><b>Initial Data</b></summary>

**Customers**


| id | limit    | initial balance |
|----|----------|-----------------|
| 1  | 100000   | 0               |
| 2  | 80000    | 0               |
| 3  | 1000000  | 0               |
| 4  | 10000000 | 0               |
| 5  | 500000   | 0               |

</details>

<details open>
      <summary><b>How to Use</b></summary>

1. Make sure Docker is installed on your machine.
2. Clone this repository to your local environment.
3. Navigate to the project directory.
4. In the terminal, execute the following command to build and start the Docker container:
   - On Linux, run:
       ```bash
       docker compose up
       ```

   - On Windows, run:
       ```bash
       docker-compose up
       ```

5. After the containers are built and the application is started, access [Scallar](http://localhost:8080/scallar.html) to view its documentation. You should see the following screen:![account-api](scallar-screen.png)


**Notes:**
- Ensure that ports `9999`, `8081`, `8082`, and `5432` are not being used by another application on your system to avoid conflicts. If necessary, you can modify the port mapping in the [docker-compose.yaml](docker-compose.yaml) file.
</details>
