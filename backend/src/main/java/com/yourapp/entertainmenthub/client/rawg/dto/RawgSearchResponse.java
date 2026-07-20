package com.yourapp.entertainmenthub.client.rawg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgSearchResponse(List<RawgGameResult> results) {}
