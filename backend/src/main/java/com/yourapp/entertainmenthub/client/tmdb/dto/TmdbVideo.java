package com.yourapp.entertainmenthub.client.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbVideo(String key, String name, String site, String type, boolean official) {}
