# Saga Pattern
The Saga pattern is the solution to implementing business transactions spanning multiple microservices.

A Saga is basically a sequence of local transactions. For every transaction performed within a Saga, the service performing the transaction publishes an event. The subsequent transaction is triggered based on the output of the previous transaction. And if one of the transactions in this chain fails, the Saga executes a series of compensating transactions to undo the impact of all the previous transactions.

# camunda BPM - Getting Started with camunda BPM and the Spring Framework

This Repository contains the 2 examples process application for the guide at [camunda.org](http://camunda.org/get-started/spring-framework.html).
1. using xml (loan)
2. javaapi (trip)

Every step of the tutorial was tagged in this repository. You can jump to the final state of each step
by the following command:

```
git checkout -f Step-X
```

If you want to follow the tutorial along please clone this repository and checkout the `Start` tag.

```
git clone https://github.com/camunda/camunda-get-started-spring.git
git checkout -f Start
mvn jetty:run -Djetty.http.port=9999

http://localhost:8094
demo
demo
cockpit
process definition
trip


```
