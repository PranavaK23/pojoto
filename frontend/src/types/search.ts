import { MediaItem } from "./media";
import { GameItem } from "./game";

export interface SearchResult {
  media: MediaItem[];
  games: GameItem[];
  page: number;
  totalPages: number;
}
