# Arquitetura Técnica — Assistente Local Universal de Guararema

**Data:** 14/02/2026  
**Timezone:** America/Sao_Paulo

## Backend
- Java 21 + Spring Boot 3.5.x
- Spring Web (REST), Validation, Security (API key/JWT)
- Spring Data JPA + Hibernate
- Spring Cache + RedisTemplate
- Actuator + Micrometer
- Springdoc OpenAPI

## Dados
- PostgreSQL 16+
- Extensões: pgvector, pg_trgm e full-text search
- Redis 7+ (cache-aside, locks leves, TTL com jitter)

## IA
- MVP quase-zero custo: provider barato/free-tier + cache agressivo
- Evolução: Ollama em VPS com fallback externo
- Opcional: LangChain4j para tools e pipeline modular

## Componentes
### Frontend (Next.js)
- Chat texto
- Envio de áudio
- Geolocalização do usuário
- Cards de resultados (distância, preço, contatos, como chegar)

### API (Spring)
- `/api/v1/chat/text`
- `/api/v1/chat/audio`
- `/api/v1/chat/location`
- `/api/v1/admin/ingest/run`

Pipeline barato -> caro:
1. Normalização e sinônimos
2. Intent routing
3. Query estruturada (SQL/FTS/pg_trgm)
4. Vetor (pgvector) quando necessário
5. Resposta final (sem LLM quando possível)
6. Cache Redis

### Banco
Entidades mínimas:
- listing
- event
- source_document
- embedding_chunk
- term_synonym
- intent_alias_map

## Regras de fallback
- Similaridade por pg_trgm
- Mapeamento por intent_alias_map/synonyms
- Top 3 locais com `CATEGORY_FALLBACK`
- Nunca sugerir outras cidades automaticamente

## Segurança MVP
- HTTPS
- Rate limit por IP/chave
- Admin/webhook com API key/JWT
- Segredos em env
- Sem logs sensíveis
