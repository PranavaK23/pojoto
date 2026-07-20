import axiosClient from "./axiosClient";
import { MediaDetail } from "../types/media";

export async function getMovieDetail(id: number): Promise<MediaDetail> {
  const { data } = await axiosClient.get<MediaDetail>(`/movies/${id}`);
  return data;
}
