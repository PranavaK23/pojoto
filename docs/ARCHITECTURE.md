# Architecture Notes

## Why provider isolation matters

Every external API (TMDb, RAWG, Watchmode, and future providers like IGDB/Jikan/CheapShark) lives behind
an interface in its own `client/<provider>` package. Services never call an external SDK or `WebClient`
directly — they depend on the interface. Swapping a provider, adding a fallback, or retiring one is a
change contained to a single package.

## Why normalized DTOs

Movies, TV shows, and (later) anime/cartoons all normalize into one `MediaItemDto` shape server-side.
Games get their own `GameItemDto` (different enough — PC requirements, platforms, store links — that
forcing them into `MediaItemDto` would mean a pile of nullable fields). This is why category tabs in
search are trivial, and why adding anime later is a mapping change, not a new endpoint.

## Why parallel provider calls

`SearchService` fires calls to each provider concurrently via `CompletableFuture` and joins them.
Total latency is `max(provider_1, provider_2, ...)` instead of the sum — this is the seam that keeps
search fast as more providers are added.

## Why Flyway + `ddl-auto: validate`

Schema is version-controlled from commit one. Hibernate never auto-generates or silently drifts schema
across environments — Flyway migrations are the single source of truth.
