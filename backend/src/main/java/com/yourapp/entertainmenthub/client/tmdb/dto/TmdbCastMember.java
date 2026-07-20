package com.yourapp.entertainmenthub.client.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbCastMember(long id, String name, String character, String profile_path, int order) {}
