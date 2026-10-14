
-- 1. plans table and seed data --
CREATE TABLE plans (id UUID PRIMARY KEY, name VARCHAR(32) NOT NULL UNIQUE, created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP );
 
INSERT INTO plans(id,name) VALUES ('00000000-0000-7000-8000-000000000001','FREE');
INSERT INTO plans(id,name) VALUES ('00000000-0000-7000-8000-000000000002','PREMIUM');
INSERT INTO plans(id,name) VALUES ('00000000-0000-7000-8000-000000000003','BUSINESS');

 -- 2. users table --
CREATE TABLE users(
        id UUID PRIMARY KEY, 
        email VARCHAR(320) NOT NULL ,
        password_hash VARCHAR(255)  NOT NULL, 
        plan_id UUID  NOT NULL REFERENCES plans(id),
        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP 
);
CREATE UNIQUE INDEX users_lower_email_unique ON users (LOWER(email));   

-- 3. destinations table --
CREATE TABLE destinations(
    id UUID PRIMARY KEY,
    original_url TEXT NOT NULL,
    normalized_url TEXT NOT NULL UNIQUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 4. short code registry table --
CREATE TABLE short_code_registry(
    short_code VARCHAR(32) PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 5. links table --
CREATE TABLE links(
    id UUID PRIMARY KEY,
    user_id UUID NULL REFERENCES users(id),
    destination_id UUID NOT NULL REFERENCES destinations(id),
    short_code VARCHAR(32) NOT NULL UNIQUE REFERENCES short_code_registry(short_code),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMPTZ NULL,
    status VARCHAR(16) NOT NULL CHECK(status IN('ACTIVE','EXPIRED','DISABLED'))
);

CREATE INDEX idx_links_user_created_at
    ON links (user_id, created_at DESC);