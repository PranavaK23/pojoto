package com.yourapp.entertainmenthub.client.tmdb;

import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbMovieDetailResponse;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbSearchResponse;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbTvDetailResponse;

public interface TmdbClient {
    TmdbSearchResponse searchMulti(String query, int page);
    TmdbMovieDetailResponse getMovieDetail(long movieId);
    TmdbTvDetailResponse getTvDetail(long tvId);
    TmdbSearchResponse getTrendingMovies();
    TmdbSearchResponse getTrendingTv();
}
