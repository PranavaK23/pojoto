package com.yourapp.entertainmenthub.dto.response;

public record StreamingPlatformDto(
        String name,
        String logoUrl,
        String type,   // "subscription" | "rent" | "buy" | "free"
        String url
) {}
