import axiosClient from "./axiosClient";
import { MediaItem } from "../types/media";
import { GameItem } from "../types/game";

export async function getTrendingMovies(): Promise<MediaItem[]> {
  const { data } = await axiosClient.get<MediaItem[]>("/trending/movies");
  return data;
}

export async function getTrendingTv(): Promise<MediaItem[]> {
  const { data } = await axiosClient.get<MediaItem[]>("/trending/tv");
  return data;
}

export async function getPopularGames(): Promise<GameItem[]> {
  const { data } = await axiosClient.get<GameItem[]>("/trending/games");
  return data;
}
