package com.yourapp.entertainmenthub.service.tvshow;

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
public class TvShowServiceImpl implements TvShowService {

    private final TmdbClient tmdbClient;
    private final WatchmodeClient watchmodeClient;
    private final TmdbToMediaDetailMapper detailMapper;
    private final WatchmodeToStreamingMapper streamingMapper;

    public TvShowServiceImpl(TmdbClient tmdbClient, WatchmodeClient watchmodeClient,
                              TmdbToMediaDetailMapper detailMapper, WatchmodeToStreamingMapper streamingMapper) {
        this.tmdbClient = tmdbClient;
        this.watchmodeClient = watchmodeClient;
        this.detailMapper = detailMapper;
        this.streamingMapper = streamingMapper;
    }

    @Override
    public MediaDetailDto getTvShowDetail(long id) {
        CompletableFuture<com.yourapp.entertainmenthub.client.tmdb.dto.TmdbTvDetailResponse> detailFuture =
                CompletableFuture.supplyAsync(() -> tmdbClient.getTvDetail(id));

        CompletableFuture<List<StreamingPlatformDto>> streamingFuture =
                CompletableFuture.supplyAsync(() -> streamingMapper.toDtoList(watchmodeClient.getStreamingSources(id, "tv")));

        CompletableFuture.allOf(detailFuture, streamingFuture).join();

        return detailMapper.fromTv(detailFuture.join(), streamingFuture.join());
    }
}
