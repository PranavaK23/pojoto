package com.yourapp.entertainmenthub.client.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbSearchResult(
        long id,
        String media_type,     // "movie" | "tv" | "person" — we filter out "person"
        String title,          // present for movies
        String name,           // present for tv shows
        String overview,
        String poster_path,
        String backdrop_path,
        Double vote_average,
        String release_date,   // movies
        String first_air_date, // tv
        List<Integer> genre_ids
) {}
