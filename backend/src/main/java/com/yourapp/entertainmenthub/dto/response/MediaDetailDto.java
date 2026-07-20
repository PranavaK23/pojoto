package com.yourapp.entertainmenthub.dto.response;

import java.util.List;

public record MediaDetailDto(
        Long id,
        String mediaType,          // "movie" | "tv"
        String title,
        String overview,
        String posterUrl,
        String backdropUrl,
        List<String> genres,
        Integer runtimeMinutes,    // movies only, null for tv
        String network,            // tv only, null for movies
        Integer numberOfSeasons,   // tv only
        Integer numberOfEpisodes,  // tv only
        String status,             // tv only ("Returning Series", "Ended", etc.)
        String releaseDate,
        Double rating,
        List<CastMemberDto> cast,
        String director,           // movies only, resolved from crew
        String trailerUrl,         // resolved YouTube URL, or null if none found
        List<StreamingPlatformDto> streamingPlatforms,
        List<MediaItemDto> similar
) {}
