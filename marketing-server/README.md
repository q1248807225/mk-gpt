# Marketing Server

A simple Spring Boot + Flowable service that exposes a minimal API for starting marketing process instances.

## How to Run

```
mvn spring-boot:run
```

The service exposes `/api/marketing/start/{key}` for starting a BPMN process.
