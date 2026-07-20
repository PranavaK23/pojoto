import { useState } from "react";
import SearchBar from "../components/ui/SearchBar";
import Skeleton from "../components/ui/Skeleton";
import MediaCard from "../components/media/MediaCard";
import GameCard from "../components/media/GameCard";
import { useSearch } from "../hooks/useSearch";

type Tab = "all" | "movies" | "tv" | "games";

export default function SearchResultsPage() {
  const [query, setQuery] = useState("");
  const [tab, setTab] = useState<Tab>("all");
  const { data, loading, error } = useSearch(query);

  const movies = data?.media.filter((m) => m.mediaType === "movie") ?? [];
  const tvShows = data?.media.filter((m) => m.mediaType === "tv") ?? [];
  const games = data?.games ?? [];

  return (
    <div>
      <div className="mx-auto max-w-2xl">
        <SearchBar value={query} onChange={setQuery} />
      </div>

      {query.trim() && (
        <>
          <div className="mt-6 flex gap-2">
            {(["all", "movies", "tv", "games"] as Tab[]).map((t) => (
              <button
                key={t}
                onClick={() => setTab(t)}
                className={`rounded-full px-4 py-1.5 text-sm capitalize transition-colors ${
                  tab === t ? "bg-indigo-500 text-white" : "bg-white/5 text-white/60 hover:bg-white/10"
                }`}
              >
                {t}
              </button>
            ))}
          </div>

          {loading && (
            <div className="mt-8 grid grid-cols-2 gap-4 sm:grid-cols-3 md:grid-cols-5">
              {Array.from({ length: 10 }).map((_, i) => (
                <Skeleton key={i} className="aspect-[2/3]" />
              ))}
            </div>
          )}

          {error && <p className="mt-8 text-red-400">{error}</p>}

          {!loading && !error && data && (
            <div className="mt-8 space-y-10">
              {(tab === "all" || tab === "movies") && movies.length > 0 && (
                <Section title="Movies">
                  <Grid>
                    {movies.map((m) => (
                      <MediaCard key={`movie-${m.id}`} item={m} />
                    ))}
                  </Grid>
                </Section>
              )}

              {(tab === "all" || tab === "tv") && tvShows.length > 0 && (
                <Section title="TV Shows">
                  <Grid>
                    {tvShows.map((m) => (
                      <MediaCard key={`tv-${m.id}`} item={m} />
                    ))}
                  </Grid>
                </Section>
              )}

              {(tab === "all" || tab === "games") && games.length > 0 && (
                <Section title="Games">
                  <Grid>
                    {games.map((g) => (
                      <GameCard key={`game-${g.id}`} item={g} />
                    ))}
                  </Grid>
                </Section>
              )}

              {movies.length === 0 && tvShows.length === 0 && games.length === 0 && (
                <p className="text-white/50">No results for "{query}".</p>
              )}
            </div>
          )}
        </>
      )}
    </div>
  );
}

function Section({ title, children }: { title: string; children: React.ReactNode }) {
  return (
    <section>
      <h2 className="mb-4 text-lg font-semibold">{title}</h2>
      {children}
    </section>
  );
}

function Grid({ children }: { children: React.ReactNode }) {
  return <div className="grid grid-cols-2 gap-4 sm:grid-cols-3 md:grid-cols-5">{children}</div>;
}
