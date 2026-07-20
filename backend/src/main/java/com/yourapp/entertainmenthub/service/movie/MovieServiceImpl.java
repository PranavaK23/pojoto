package com.yourapp.entertainmenthub.service.movie;

import com.yourapp.entertainmenthub.client.tmdb.TmdbClient;
import com.yourapp.entertainmenthub.client.watchmode.WatchmodeClient;
import com.yourapp.entertainmenthub.dto.response.MediaDetailDto;
import com.yourapp.entertainmenthub.dto.response.StreamingPlatformDto;
import com.yourapp.entertainmenthub.mapper.TmdbToMediaDetailMapper;
import com.yourapp.entertainmenthub.mapper.WatchmodeToStreamingMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieServiceImpl implements MovieService {

    private final TmdbClient tmdbClient;
    private final WatchmodeClient watchmodeClient;
    private final TmdbToMediaDetailMapper detailMapper;
    private final WatchmodeToStreamingMapper streamingMapper;

    public MovieServiceImpl(TmdbClient tmdbClient, WatchmodeClient watchmodeClient,
                             TmdbToMediaDetailMapper detailMapper, WatchmodeToStreamingMapper streamingMapper) {
        this.tmdbClient = tmdbClient;
        this.watchmodeClient = watchmodeClient;
        this.detailMapper = detailMapper;
        this.streamingMapper = streamingMapper;
    }

    @Override
    public MediaDetailDto getMovieDetail(long id) {
        // Movie metadata and streaming availability come from two different providers —
        // fire both concurrently, same pattern as SearchService.
        CompletableFuture<com.yourapp.entertainmenthub.client.tmdb.dto.TmdbMovieDetailResponse> detailFuture =
                CompletableFuture.supplyAsync(() -> tmdbClient.getMovieDetail(id));

        CompletableFuture<List<StreamingPlatformDto>> streamingFuture =
                CompletableFuture.supplyAsync(() -> streamingMapper.toDtoList(watchmodeClient.getStreamingSources(id, "movie")));

        CompletableFuture.allOf(detailFuture, streamingFuture).join();

        return detailMapper.fromMovie(detailFuture.join(), streamingFuture.join());
    }
}
