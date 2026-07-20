package com.yourapp.entertainmenthub.dto.response;

public record CastMemberDto(
        Long id,
        String name,
        String character,
        String profileUrl
) {}
