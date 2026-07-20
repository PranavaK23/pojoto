package com.yourapp.entertainmenthub.dto.response;

import java.util.List;

public record MediaItemDto(
        Long id,                  // TMDb id
        String mediaType,         // "movie" | "tv"
        String title,
        String overview,
        String posterUrl,         // fully-formed URL, frontend builds nothing
        String backdropUrl,
        Double rating,
        String releaseDate,
        List<String> genres       // resolved names, not raw ids
) {}
