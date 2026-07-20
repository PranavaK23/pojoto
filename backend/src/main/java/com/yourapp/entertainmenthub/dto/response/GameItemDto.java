package com.yourapp.entertainmenthub.dto.response;

import java.util.List;

public record GameItemDto(
        Long id,
        String title,
        String coverUrl,
        Double rating,
        String releaseDate,
        List<String> platforms
) {}
