# My-Statistics-App
```
This app calculates realtime statistic from the last 60 seconds for the transactions
```

### Prerequisites
```
- Java 1.8
- Maven
- Git
```

## Running the tests
```
mvn clean install
```
### How to run the project

- java -jar <app.jar>
- by default the application runs on 8080 port.


### API create transaction

```
API - http://localhost:8080/transactions
POST /transactions
Payload :
{"amount": 12.3,
"timestamp": 1478192204000 } 
```

### API to get Statistics

```
API - http://localhost:8080/statistics
GET
Response
{
    "sum": 510,
    "avg": 170,
    "max": 300,
    "min": 10,
    "count": 3
}
```
### Git URL
```
ex
```
