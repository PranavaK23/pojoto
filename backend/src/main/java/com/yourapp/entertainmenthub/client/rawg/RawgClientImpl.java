package com.yourapp.entertainmenthub.client.rawg;

import com.yourapp.entertainmenthub.client.rawg.dto.RawgSearchResponse;
import com.yourapp.entertainmenthub.config.ExternalApiProperties;
import com.yourapp.entertainmenthub.exception.ExternalApiException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class RawgClientImpl implements RawgClient {

    private final WebClient webClient;
    private final ExternalApiProperties props;

    public RawgClientImpl(@Qualifier("rawgWebClient") WebClient webClient, ExternalApiProperties props) {
        this.webClient = webClient;
        this.props = props;
    }

    @Override
    @Cacheable(value = "rawg-search", key = "#query + '-' + #page")
    public RawgSearchResponse search(String query, int page) {
        try {
            return webClient.get()
                    .uri(uri -> uri.path("/games")
                            .queryParam("key", props.rawg().apiKey())
                            .queryParam("search", query)
                            .queryParam("page", page)
                            .queryParam("page_size", 10)
                            .build())
                    .retrieve()
                    .bodyToMono(RawgSearchResponse.class)
                    .timeout(java.time.Duration.ofSeconds(3))
                    .block();
        } catch (WebClientResponseException ex) {
            throw new ExternalApiException("RAWG", "RAWG search failed", ex);
        }
    }

    @Override
    @Cacheable(value = "rawg-popular")
    public RawgSearchResponse getPopular() {
        try {
            return webClient.get()
                    .uri(uri -> uri.path("/games")
                            .queryParam("key", props.rawg().apiKey())
                            .queryParam("ordering", "-added")
                            .queryParam("page_size", 20)
                            .build())
                    .retrieve()
                    .bodyToMono(RawgSearchResponse.class)
                    .timeout(java.time.Duration.ofSeconds(3))
                    .block();
        } catch (WebClientResponseException ex) {
            throw new ExternalApiException("RAWG", "RAWG popular fetch failed", ex);
        }
    }
}
