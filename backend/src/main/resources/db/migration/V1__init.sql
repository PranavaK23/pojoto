-- Initial schema: prepares the database for future auth/personalization features.
-- Auth is NOT enforced yet (see SecurityConfig) but the schema is ready for it.

CREATE TABLE app_user (
    id            BIGSERIAL PRIMARY KEY,
    email         VARCHAR(255) NOT NULL UNIQUE,
    display_name  VARCHAR(100),
    created_at    TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE favorite (
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    media_type     VARCHAR(20) NOT NULL,   -- 'movie' | 'tv' | 'game'
    external_id    BIGINT NOT NULL,        -- id from the source provider (TMDb / RAWG)
    title          VARCHAR(255) NOT NULL,
    poster_url     TEXT,
    created_at     TIMESTAMP NOT NULL DEFAULT now(),
    UNIQUE (user_id, media_type, external_id)
);

CREATE TABLE watchlist (
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    media_type     VARCHAR(20) NOT NULL,
    external_id    BIGINT NOT NULL,
    title          VARCHAR(255) NOT NULL,
    poster_url     TEXT,
    created_at     TIMESTAMP NOT NULL DEFAULT now(),
    UNIQUE (user_id, media_type, external_id)
);

CREATE TABLE search_history (
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT REFERENCES app_user(id) ON DELETE CASCADE,
    query          VARCHAR(255) NOT NULL,
    searched_at    TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_favorite_user ON favorite(user_id);
CREATE INDEX idx_watchlist_user ON watchlist(user_id);
CREATE INDEX idx_search_history_user ON search_history(user_id);
