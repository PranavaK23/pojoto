export interface GameItem {
  id: number;
  title: string;
  coverUrl: string | null;
  rating: number | null;
  releaseDate: string | null;
  platforms: string[];
}
