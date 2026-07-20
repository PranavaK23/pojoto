package com.yourapp.entertainmenthub.client.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbMovieDetailResponse(
        long id,
        String title,
        String overview,
        String poster_path,
        String backdrop_path,
        List<TmdbGenre> genres,
        Integer runtime,
        String release_date,
        Double vote_average,
        TmdbCredits credits,
        TmdbVideoResults videos,
        TmdbSimilarResults similar
) {}
