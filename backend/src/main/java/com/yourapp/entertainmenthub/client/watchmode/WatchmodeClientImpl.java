package com.yourapp.entertainmenthub.client.watchmode;

import com.yourapp.entertainmenthub.client.watchmode.dto.WatchmodeSource;
import com.yourapp.entertainmenthub.config.ExternalApiProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Component
public class WatchmodeClientImpl implements WatchmodeClient {

    private final WebClient webClient;
    private final ExternalApiProperties props;

    public WatchmodeClientImpl(@Qualifier("watchmodeWebClient") WebClient webClient, ExternalApiProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    @Override
    @Cacheable(value = "watchmode-sources", key = "#tmdbId + '-' + #tmdbType")
    public List<WatchmodeSource> getStreamingSources(long tmdbId, String tmdbType) {
        try {
            // Watchmode supports lookup by TMDb id directly via the "tmdb_id" search field
            WatchmodeSource[] sources = webClient.get()
                    .uri(uri -> uri.path("/title/{tmdbId}-{tmdbType}/sources/")
                            .queryParam("apiKey", props.watchmode().apiKey())
                            .build(tmdbId, tmdbType))
                    .retrieve()
                    .bodyToMono(WatchmodeSource[].class)
                    .block();
            return sources == null ? List.of() : List.of(sources);
        } catch (WebClientResponseException.NotFound ex) {
            // Perfectly normal — not every title has streaming data. Not an error.
            return List.of();
        } catch (WebClientResponseException ex) {
            // Streaming info is supplementary, not critical — degrade gracefully instead of failing the whole detail page
            return List.of();
        }
    }
}
