package com.yourapp.entertainmenthub.client.rawg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgGameDetailResponse(
        long id,
        String name,
        String description_raw,
        String background_image,
        Double rating,
        String released,
        String developers_text,
        String publishers_text,
        List<RawgGameResult.RawgPlatformWrapper> platforms,
        List<RawgGenre> genres,
        List<RawgStore> stores
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RawgGenre(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RawgStore(RawgStoreDetail store) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RawgStoreDetail(String name, String domain) {}
}
