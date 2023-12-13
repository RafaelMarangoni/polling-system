# Project Polling System

## Descrição
Este projeto foi criado para viabilizar se as paltas devem ser discutas ou não, por um sistema de votação automatizado

## Pré-requisitos
- Java 17+
- BD Postgresql.

## Instalação
1. https://github.com/RafaelMarangoni/polling-system.git.

## Endpoints/API

## Swagger Link

- https://polling-system-kwaz.onrender.com/swagger-ui/index.html

### GET /count-votes
- Descrição:
    - Endpoint com o intuito de retornar quantos votos tem em cada pauta criada.
    - Ele recebe uma query param que deve conter o exato nome da palta
    - {
      "title": "teste16v2",
      "sim": "1",
      "nao": "0",
      "totalGeral": "1"
      }

### GET /get-agenda-enabled
- Descrição:
    - Endpoint com o intuito de retornar todas as paltas que estão ativas.
    - Ele recebe uma query param que deve conter o exato nome da palta
        - Return:
            - "agendaEnabled": [
              {
              "id": 7,
              "startSubject": "2023-12-11T22:14:16.000+00:00",
              "timeSubject": 30,
              "endSubject": "2023-12-11T22:44:16.000+00:00",
              "titleSubject": "teste16v2"
              }
              ],
              "message": "all guidelines available"

### POST /create-subject
- Descrição:
    - Endpoint para criar as pautas a serem votadas
        - Request:
            - {
              "titleSubject": "teste16v2",
              "timeDuration": 30

          }
        - Return:
            - {
              "message": "agenda created successfully"
              }

### POST /vote
- Descrição:
    - Endpoint para ser computado os votos dos associados, contendo a regra de que cada assossiado com o teu ID unico o CPF, não poderá votar duas vezes na mesma palta e deverá ter um CPF válido
    - Request:
      {
      "numberOfSubject": 7,
      "associateIdentification": "97189905006",
      "vote":true,
      "title":"teste16v2"
      }
    - Return
        - {
          "message": "agenda created successfully"
          }


## Estrutura do Projeto
- Estrutura está divido em Controllers, Services e Repositórios, e suas devidas interfaces.
