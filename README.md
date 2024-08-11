# TransactionSimulator
Simulating Credit Card Transactions

##Dependencies
Install Docker.
Install Docker Compose.

Create two folders :
  - ~/data/postgres
  - ~/data/grafana
  - ~/data/grafana_data

##Steps to run
- Docker compose should be able to bring up entire stack :
    docker compose -f simulation_compose.yml build
    docker compose -f simulation_compose.yml up

Producer is available at : 
  http://localhost:8080
Swagger for testing:
  http://localhost:8080/swagger-ui
Currently producer is blocking till all the messages are sent.
Sample data with credit cards is already setup in db.

#Steps to check DLQ
Run a bulk message submit (e.g. 500 msgs in 60 secs)
Stop the autherization-app container.
Consumer will start pushing messages to DLQ after 3 retries.
Can be checked in logs.

Prometheus and Grafana are configured for JVM stats for all apps and tracing for producer-app.
Prometheus endpoint : http://localhost:9090/
Grafana endpoint : http://localhost:3000/



