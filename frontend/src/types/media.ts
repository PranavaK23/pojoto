export interface MediaItem {
  id: number;
  mediaType: "movie" | "tv";
  title: string;
  overview: string;
  posterUrl: string | null;
  backdropUrl: string | null;
  rating: number | null;
  releaseDate: string | null;
  genres: string[];
}

export interface CastMember {
  id: number;
  name: string;
  character: string;
  profileUrl: string | null;
}

export interface StreamingPlatform {
  name: string;
  logoUrl: string | null;
  type: "sub" | "rent" | "buy" | "free";
  url: string;
}

export interface MediaDetail {
  id: number;
  mediaType: "movie" | "tv";
  title: string;
  overview: string;
  posterUrl: string | null;
  backdropUrl: string | null;
  genres: string[];
  runtimeMinutes: number | null;
  network: string | null;
  numberOfSeasons: number | null;
  numberOfEpisodes: number | null;
  status: string | null;
  releaseDate: string | null;
  rating: number | null;
  cast: CastMember[];
  director: string | null;
  trailerUrl: string | null;
  streamingPlatforms: StreamingPlatform[];
  similar: MediaItem[];
}
