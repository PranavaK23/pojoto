package com.yourapp.entertainmenthub.dto.response;

import java.util.List;

public record SearchResultDto(
        List<MediaItemDto> media,
        List<GameItemDto> games,
        int page,
        int totalPages
) {}
