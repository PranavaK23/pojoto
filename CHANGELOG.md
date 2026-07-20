# Changelog

All notable changes to this project are documented here.
Format loosely follows [Keep a Changelog](https://keepachangelog.com/).

## [Unreleased]

### Added
- Backend scaffold: Spring Boot 3.3, Java 21, Flyway, Caffeine cache, WebClient config
- Global exception handling (`ApiError`, `GlobalExceptionHandler`, `ExternalApiException`, `ResourceNotFoundException`)
- TMDb client (`TmdbClient`) — multi-search, movie/TV detail, trending — with per-endpoint response caching
- RAWG client (`RawgClient`) — game search, popular games — with response caching
- Provider DTO isolation: raw TMDb/RAWG shapes never leave the `client/*/dto` packages
- Normalization mappers: `TmdbToMediaItemMapper`, `RawgToGameItemMapper` producing unified `MediaItemDto` / `GameItemDto`
- `SearchService` — parallel TMDb + RAWG orchestration via `CompletableFuture`, merged into one `SearchResultDto`
- `GET /api/v1/search` endpoint
- Initial Flyway migration (`V1__init.sql`) — `app_user`, `favorite`, `watchlist`, `search_history` tables
- Frontend scaffold: Vite + React + TypeScript + Tailwind, Axios client, typed search API, working search results page wired to the backend
- Watchmode client (`WatchmodeClient`) — streaming source lookup by TMDb id, degrades gracefully (empty list) when a title has no streaming data instead of failing the page
- `MediaDetailDto` + `TmdbToMediaDetailMapper` — full movie/TV detail shape: cast, director, trailer (resolved YouTube URL), streaming platforms, similar titles
- `MovieService` / `TvShowService` — orchestrate TMDb detail + Watchmode streaming in parallel, same `CompletableFuture` pattern as search
- `GET /api/v1/movies/{id}` and `GET /api/v1/tv/{id}` endpoints
- `TrendingService` + `GET /api/v1/trending/{movies,tv,games}` endpoints (backend groundwork for the homepage)
- JPA entities (`AppUser`, `Favorite`, `Watchlist`, `SearchHistory`) and repositories matching the `V1__init.sql` schema
- `SecurityConfig` — permit-all filter chain, structured so a `JwtAuthFilter` can be inserted later without rework
- `WebConfig` — CORS for the Vite dev server

### Planned (next steps)
- Game detail endpoint with PC requirements (RAWG detail mapper)
- Homepage UI consuming the trending endpoints
- Movie/TV/game detail pages in the frontend
- Search filters (genre, year, rating, platform)
- JWT authentication activation
