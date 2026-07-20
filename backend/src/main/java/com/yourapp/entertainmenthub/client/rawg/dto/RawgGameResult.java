package com.yourapp.entertainmenthub.client.rawg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgGameResult(
        long id,
        String name,
        String background_image,
        Double rating,
        String released,
        List<RawgPlatformWrapper> platforms
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RawgPlatformWrapper(RawgPlatform platform) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RawgPlatform(String name) {}
}
