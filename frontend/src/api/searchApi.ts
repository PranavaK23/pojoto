import axiosClient from "./axiosClient";
import { SearchResult } from "../types/search";

export async function searchAll(query: string, page = 1): Promise<SearchResult> {
  const { data } = await axiosClient.get<SearchResult>("/search", {
    params: { q: query, page },
  });
  return data;
}
