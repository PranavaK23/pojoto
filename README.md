# Entertainment Hub

A production-grade search platform for movies, TV shows, anime, and video games — built as a scalable
foundation that grows into a full entertainment hub (streaming availability, PC requirements, reviews, and more).

## Stack

**Backend:** Java 21, Spring Boot 3.3, Spring Data JPA, PostgreSQL, Flyway, Caffeine cache, Maven
**Frontend:** React, Vite, TypeScript, Tailwind CSS, React Router, Axios, Framer Motion

## Architecture

- REST API, layered architecture (`controller → service → repository`)
- Every external API (TMDb, RAWG, Watchmode) isolated behind its own `client` package — swapping or adding
  a provider never touches business logic
- Raw provider responses are normalized into stable internal DTOs (`MediaItemDto`, `GameItemDto`) before
  they ever reach the frontend
- Search orchestration calls providers in parallel (`CompletableFuture`) so latency scales with the
  slowest provider, not the sum of all of them

Full architecture notes: see [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md).

## Getting Started

### Backend

```bash
cd backend
cp src/main/resources/application-dev.yml.example src/main/resources/application-local.yml  # if present
export TMDB_API_KEY=your_key
export WATCHMODE_API_KEY=your_key
export RAWG_API_KEY=your_key
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Requires a local PostgreSQL database `entertainment_hub_dev`.

### Frontend

```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```

## Status

Version 1 in progress — see [`CHANGELOG.md`](CHANGELOG.md) for what's done and what's next.
