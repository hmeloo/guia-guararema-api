up:
	docker compose --env-file .env up -d --build

down:
	docker compose down

logs:
	docker compose logs -f backend

test:
	mvn test

lint:
	mvn -q -DskipTests verify

migrate:
	mvn -q -DskipTests flyway:migrate

seed:
	@echo "Seeds via Flyway V2__seed_initial_data.sql"
