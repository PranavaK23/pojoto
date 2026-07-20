package com.yourapp.entertainmenthub.service.search;

import com.yourapp.entertainmenthub.client.rawg.RawgClient;
import com.yourapp.entertainmenthub.client.tmdb.TmdbClient;
import com.yourapp.entertainmenthub.dto.response.GameItemDto;
import com.yourapp.entertainmenthub.dto.response.MediaItemDto;
import com.yourapp.entertainmenthub.dto.response.SearchResultDto;
import com.yourapp.entertainmenthub.mapper.RawgToGameItemMapper;
import com.yourapp.entertainmenthub.mapper.TmdbToMediaItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImpl implements SearchService {

    private final TmdbClient tmdbClient;
    private final RawgClient rawgClient;
    private final TmdbToMediaItemMapper mediaMapper;
    private final RawgToGameItemMapper gameMapper;

    public SearchServiceImpl(TmdbClient tmdbClient, RawgClient rawgClient,
                              TmdbToMediaItemMapper mediaMapper, RawgToGameItemMapper gameMapper) {
        this.tmdbClient = tmdbClient;
        this.rawgClient = rawgClient;
        this.mediaMapper = mediaMapper;
        this.gameMapper = gameMapper;
    }

    @Override
    public SearchResultDto search(String query, int page) {
        // Fire both providers concurrently — total latency ~= max(tmdb, rawg), not the sum
        CompletableFuture<List<MediaItemDto>> mediaFuture = CompletableFuture.supplyAsync(() ->
                tmdbClient.searchMulti(query, page).results().stream()
                        .filter(r -> "movie".equals(r.media_type()) || "tv".equals(r.media_type()))
                        .map(mediaMapper::toDto)
                        .toList()
        );

        CompletableFuture<List<GameItemDto>> gamesFuture = CompletableFuture.supplyAsync(() ->
                rawgClient.search(query, page).results().stream()
                        .map(gameMapper::toDto)
                        .toList()
        );

        CompletableFuture.allOf(mediaFuture, gamesFuture).join();

        return new SearchResultDto(
                mediaFuture.join(),
                gamesFuture.join(),
                page,
                1 // total pages — refined once provider-level pagination is normalized
        );
    }
}
