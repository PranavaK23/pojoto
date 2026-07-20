package com.yourapp.entertainmenthub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external-api")
public record ExternalApiProperties(
        Provider tmdb,
        Provider watchmode,
        Provider rawg
) {
    public record Provider(String baseUrl, String apiKey, String imageBaseUrl) {}
}
