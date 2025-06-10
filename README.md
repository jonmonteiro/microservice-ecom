## How to Run the Project

- docker-compose up -d
- Create a topic for the first time: docker exec kafka kafka-topics --create --topic estoque-topico --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
- Run all microservices
---

