package com.yourapp.entertainmenthub.client.watchmode.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WatchmodeSource(
        String name,
        String type,       // "sub" | "rent" | "buy" | "free"
        String web_url,
        String logo_100px
) {}
