package com.yourapp.entertainmenthub.client.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbTvDetailResponse(
        long id,
        String name,
        String overview,
        String poster_path,
        String backdrop_path,
        List<TmdbGenre> genres,
        List<TmdbNetwork> networks,
        Integer number_of_seasons,
        Integer number_of_episodes,
        String status,
        String first_air_date,
        Double vote_average,
        TmdbCredits credits,
        TmdbVideoResults videos
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record TmdbNetwork(long id, String name, String logo_path) {}
}
