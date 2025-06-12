## E-commerce Microservices in Java Spring Framework and Kafka 

## Running the project

Make sure to run the following commands:

### Create Containers

```bash
docker-compose up -d
```

### Create Topic
```bash
 docker exec kafka kafka-topics --create --topic estoque-topico --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
```
###
- Run all microservices

---

