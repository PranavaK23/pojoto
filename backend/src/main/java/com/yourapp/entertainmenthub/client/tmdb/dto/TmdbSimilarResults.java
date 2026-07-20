package com.yourapp.entertainmenthub.client.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbSimilarResults(List<TmdbSearchResult> results) {}
