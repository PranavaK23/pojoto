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
        // Run sequentially to avoid ForkJoinPool deadlocks on 1-core instances
        List<MediaItemDto> mediaItems = java.util.Collections.emptyList();
        try {
            mediaItems = tmdbClient.searchMulti(query, page).results().stream()
                    .filter(r -> "movie".equals(r.media_type()) || "tv".equals(r.media_type()))
                    .map(mediaMapper::toDto)
                    .toList();
        } catch (Exception ex) {
            org.slf4j.LoggerFactory.getLogger(SearchServiceImpl.class).error("TMDB search failed", ex);
        }

        List<GameItemDto> gameItems = java.util.Collections.emptyList();
        try {
            gameItems = rawgClient.search(query, page).results().stream()
                    .map(gameMapper::toDto)
                    .toList();
        } catch (Exception ex) {
            org.slf4j.LoggerFactory.getLogger(SearchServiceImpl.class).error("RAWG search failed", ex);
        }

        if (mediaItems.isEmpty() && gameItems.isEmpty()) {
            throw new RuntimeException("Both TMDB and RAWG API calls failed. Please check your API keys in Render.");
        }

        return new SearchResultDto(
                mediaItems,
                gameItems,
                page,
                1
        );
    }
}
