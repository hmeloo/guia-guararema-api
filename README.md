# Guia Guararema Backend (MVP)

Backend em Java 21 + Spring Boot 3.5.x para assistente local de Guararema.

## Stack
- Java 21
- Spring Boot 3.5.x (Web, Validation, Security, Data JPA, Redis, Actuator)
- PostgreSQL 16+ (pg_trgm + FTS + pgvector)
- Redis 7+
- Flyway
- OpenAPI (springdoc + `openapi.json`)
- Testes: JUnit 5, Mockito, Testcontainers

## WSL2 Setup
1. Instalar Docker Desktop com integração WSL2.
2. Instalar Java 21 e Maven 3.9+.
3. Copiar env:
   ```bash
   cp .env.example .env
   ```

## Subir ambiente
```bash
make up
```

## Endpoints
- `POST /api/v1/chat/text`
- `POST /api/v1/chat/audio`
- `POST /api/v1/chat/location`
- `POST /api/v1/admin/ingest/run` (API key admin)
- `GET /api/v1/health`

## cURL
```bash
curl -X POST http://localhost:8080/api/v1/chat/text \
  -H 'Content-Type: application/json' \
  -d '{"message":"tem pet shoop perto de mim aberto agora?","latitude":-23.48,"longitude":-46.04,"when":"now"}'

curl -X POST http://localhost:8080/api/v1/chat/location \
  -H 'Content-Type: application/json' \
  -d '{"latitude":-23.48,"longitude":-46.04,"query":"farmácia"}'

curl -X POST http://localhost:8080/api/v1/admin/ingest/run \
  -H 'X-API-KEY: dev-admin-key'
```

## Testes
```bash
make test
```

## Variáveis de ambiente
Veja `.env.example`.

## Arquitetura (Clean)
```text
[domain] <- [application/usecases] <- [infrastructure/adapters/controllers]
```

## Padrões implementados
- Strategy: retrieval por intent
- Factory: seleção de strategy
- Template Method: pipeline base de chat
- Adapter: Redis/JPA/STT/LLM
- Specification: category hint para fallback
- Builder: montagem de ChatResult

## Observabilidade e segurança
- Correlation ID por request
- Actuator + Prometheus metrics
- Métricas custom: cache_hit_total, cache_miss_total, fallback_total, no_result_total, llm_usage_total
- API key para endpoint admin
- CORS por env
- Rate limit básico por IP
