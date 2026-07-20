import axiosClient from "./axiosClient";
import { MediaDetail } from "../types/media";

export async function getTvShowDetail(id: number): Promise<MediaDetail> {
  const { data } = await axiosClient.get<MediaDetail>(`/tv/${id}`);
  return data;
}
