package com.yourapp.entertainmenthub.client.tmdb;

import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbMovieDetailResponse;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbSearchResponse;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbTvDetailResponse;
import com.yourapp.entertainmenthub.exception.ExternalApiException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class TmdbClientImpl implements TmdbClient {

    private final WebClient webClient;

    public TmdbClientImpl(@Qualifier("tmdbWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Cacheable(value = "tmdb-search", key = "#query + '-' + #page")
    public TmdbSearchResponse searchMulti(String query, int page) {
        return get("/search/multi", uri -> uri
                        .queryParam("query", query)
                        .queryParam("page", page)
                        .queryParam("include_adult", false)
                        .build(),
                TmdbSearchResponse.class);
    }

    @Override
    @Cacheable(value = "tmdb-movie-detail", key = "#movieId")
    public TmdbMovieDetailResponse getMovieDetail(long movieId) {
        return get("/movie/" + movieId, uri -> uri
                        .queryParam("append_to_response", "credits,videos,similar")
                        .build(),
                TmdbMovieDetailResponse.class);
    }

    @Override
    @Cacheable(value = "tmdb-tv-detail", key = "#tvId")
    public TmdbTvDetailResponse getTvDetail(long tvId) {
        return get("/tv/" + tvId, uri -> uri
                        .queryParam("append_to_response", "credits,videos")
                        .build(),
                TmdbTvDetailResponse.class);
    }

    @Override
    @Cacheable(value = "tmdb-trending-movies")
    public TmdbSearchResponse getTrendingMovies() {
        return get("/trending/movie/week", uri -> uri.build(), TmdbSearchResponse.class);
    }

    @Override
    @Cacheable(value = "tmdb-trending-tv")
    public TmdbSearchResponse getTrendingTv() {
        return get("/trending/tv/week", uri -> uri.build(), TmdbSearchResponse.class);
    }

    private <T> T get(String path, java.util.function.Function<org.springframework.web.util.UriBuilder, java.net.URI> uriFn, Class<T> responseType) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriFn.apply(uriBuilder.path(path)))
                    .retrieve()
                    .bodyToMono(responseType)
                    .block();
        } catch (WebClientResponseException ex) {
            throw new ExternalApiException("TMDb", "TMDb call failed: " + path, ex);
        }
    }
}
