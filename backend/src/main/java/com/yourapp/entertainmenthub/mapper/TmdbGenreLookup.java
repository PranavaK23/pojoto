package com.yourapp.entertainmenthub.mapper;

import java.util.Map;

public final class TmdbGenreLookup {

    private TmdbGenreLookup() {}

    // TMDb's genre list is effectively static — safe to hardcode, avoids an extra API call per search
    private static final Map<Integer, String> GENRES = Map.ofEntries(
            Map.entry(28, "Action"), Map.entry(12, "Adventure"), Map.entry(16, "Animation"),
            Map.entry(35, "Comedy"), Map.entry(80, "Crime"), Map.entry(18, "Drama"),
            Map.entry(14, "Fantasy"), Map.entry(27, "Horror"), Map.entry(9648, "Mystery"),
            Map.entry(10749, "Romance"), Map.entry(878, "Sci-Fi"), Map.entry(53, "Thriller"),
            Map.entry(10751, "Family"), Map.entry(99, "Documentary"), Map.entry(36, "History"),
            Map.entry(10402, "Music"), Map.entry(10752, "War"), Map.entry(37, "Western")
    );

    public static java.util.List<String> resolve(java.util.List<Integer> ids) {
        if (ids == null) return java.util.List.of();
        return ids.stream().map(id -> GENRES.getOrDefault(id, "Unknown")).toList();
    }
}
