# Jonas Bank

## Description

This is a simple bank accounts and transfers service.

Created using [dropwizard](www.dropwizard.io). Mainly in order to be able to build and run easily. Only jersey and jackson are explicitly used.
Dependencies are wired manually from within `BankApplication`. `LinkedHashMap` is the database of choice. Money is represented using [moneta](https://javamoney.github.io) - a Reference Implementation of JSR 354.

## Tests

There are two tests included.

`ApiTest` will run the service and test exposed api endpoints using [rest-assured](http://rest-assured.io/).

`TransferProcessorTest` contains unit tests which test Transfer Processor.

## How to build and run

`mvn clean package`

`java -jar target/jonas-bank-1.0-SNAPSHOT.jar`

The service expose endpoints on port 8080. 

## Endpoints
All requests are validated

### POST /accounts
Opens a new account with balance 0.00

Request body:

```json
{
 	"holderName": "Jonas"
}
```
 
Response:
`HTTP 201`
```json
{
    "id": "13b2725f-2d7c-416d-9853-82ee52937b71"
}
```
 
### GET /accounts
Returns all accounts, oldest first. The app is started with a house account containing some funds.

Response:
`HTTP 200`

```json
[
     {
         "id": "house",
         "holderName": "Jonas Bank",
         "balance": {
             "amount": 10000000.00,
             "currency": "EUR"
         }
     },
     {
         "id": "143039c2-8134-4f7b-8e90-18e11e397724",
         "holderName": "Jonas",
         "balance": {
             "amount": 0.00,
             "currency": "EUR"
         }
     }
]
```

### GET /accounts/{id}
Response:

`HTTP 200`
```json
{
    "id": "143039c2-8134-4f7b-8e90-18e11e397724",
    "holderName": "Jonas",
    "balance": {
        "amount": 0.00,
        "currency": "EUR"
    }
}
```

### POST /transfers
Transfers funds from one account to another (or from an account to the same account, the service does not judge)

Request:
```json
{
	"amount": {"amount": 10.0, "currency": "EUR"},
	"debitAccountId": "house",
	"creditAccountId": "143039c2-8134-4f7b-8e90-18e11e397724"
}
```
Response:

`HTTP 201`
```json
{
    "id": "313b6f2c-0aab-489c-9c3d-9f725ed15dd8"
}
```

### GET /transfers
Returns all transfers, oldest first

Response:

`HTTP 200`

```json
[
    {
        "id": "ada8a4cb-ad64-4cde-9e58-e726b12bcf10",
        "amount": {
            "amount": 15.00,
            "currency": "EUR"
        },
        "debitAccountId": "house",
        "creditAccountId": "7fed4d22-f449-4ebf-bbaa-351c6812acc2",
        "transferDate": "2019-09-01T14:26:23.362Z"
    },
    {
        "id": "f3f86818-812c-44e7-bdf2-2f6a4aa3b2f7",
        "amount": {
            "amount": 20.00,
            "currency": "EUR"
        },
        "debitAccountId": "house",
        "creditAccountId": "7fed4d22-f449-4ebf-bbaa-351c6812acc2",
        "transferDate": "2019-09-01T14:26:31.071Z"
    }
]
```
### GET /transfers/{id}

Response:

`HTTP 200`

```json
{
    "id": "ada8a4cb-ad64-4cde-9e58-e726b12bcf10",
    "amount": {
        "amount": 15.00,
        "currency": "EUR"
    },
    "debitAccountId": "house",
    "creditAccountId": "7fed4d22-f449-4ebf-bbaa-351c6812acc2",
    "transferDate": "2019-09-01T14:26:23.362Z"
}
```