CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS listing (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    normalized_name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    tags TEXT[],
    description TEXT,
    address VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    phone VARCHAR(40),
    whatsapp VARCHAR(40),
    instagram VARCHAR(120),
    website VARCHAR(255),
    open_hours_json JSONB,
    source_type VARCHAR(30),
    confidence_score NUMERIC(3,2),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE IF NOT EXISTS event (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    normalized_title VARCHAR(255) NOT NULL,
    description TEXT,
    start_at TIMESTAMPTZ,
    end_at TIMESTAMPTZ,
    venue_name VARCHAR(255),
    address VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    source_url VARCHAR(255),
    source_type VARCHAR(30),
    confidence_score NUMERIC(3,2),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ DEFAULT now(),
    updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE IF NOT EXISTS source_document (
    id UUID PRIMARY KEY,
    source_name VARCHAR(120) NOT NULL,
    source_url VARCHAR(255),
    fetched_at TIMESTAMPTZ,
    content_hash VARCHAR(128) UNIQUE NOT NULL,
    raw_excerpt TEXT,
    parsed_ok BOOLEAN,
    error_message TEXT,
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE IF NOT EXISTS embedding_chunk (
    id UUID PRIMARY KEY,
    entity_type VARCHAR(20) NOT NULL,
    entity_id UUID NOT NULL,
    chunk_text TEXT NOT NULL,
    embedding vector(1536),
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE IF NOT EXISTS term_synonym (
    id UUID PRIMARY KEY,
    term VARCHAR(120) NOT NULL,
    synonym VARCHAR(120) NOT NULL,
    weight NUMERIC(3,2) DEFAULT 1.0,
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE IF NOT EXISTS intent_alias_map (
    id UUID PRIMARY KEY,
    alias VARCHAR(120) NOT NULL,
    canonical_intent VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_listing_name_trgm ON listing USING gin (normalized_name gin_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_listing_category ON listing(category);
CREATE INDEX IF NOT EXISTS idx_listing_tags ON listing USING gin(tags);
CREATE INDEX IF NOT EXISTS idx_listing_fts ON listing USING gin(to_tsvector('portuguese', coalesce(name,'') || ' ' || coalesce(description,'')));
CREATE INDEX IF NOT EXISTS idx_event_start_end ON event(start_at, end_at);
CREATE INDEX IF NOT EXISTS idx_event_fts ON event USING gin(to_tsvector('portuguese', coalesce(title,'') || ' ' || coalesce(description,'')));
CREATE INDEX IF NOT EXISTS idx_embedding_vector ON embedding_chunk USING hnsw (embedding vector_cosine_ops);
