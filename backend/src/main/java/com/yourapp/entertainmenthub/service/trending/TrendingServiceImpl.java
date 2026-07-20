package com.yourapp.entertainmenthub.service.trending;

import com.yourapp.entertainmenthub.client.rawg.RawgClient;
import com.yourapp.entertainmenthub.client.tmdb.TmdbClient;
import com.yourapp.entertainmenthub.dto.response.GameItemDto;
import com.yourapp.entertainmenthub.dto.response.MediaItemDto;
import com.yourapp.entertainmenthub.mapper.RawgToGameItemMapper;
import com.yourapp.entertainmenthub.mapper.TmdbToMediaItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendingServiceImpl implements TrendingService {

    private final TmdbClient tmdbClient;
    private final RawgClient rawgClient;
    private final TmdbToMediaItemMapper mediaMapper;
    private final RawgToGameItemMapper gameMapper;

    public TrendingServiceImpl(TmdbClient tmdbClient, RawgClient rawgClient,
                                TmdbToMediaItemMapper mediaMapper, RawgToGameItemMapper gameMapper) {
        this.tmdbClient = tmdbClient;
        this.rawgClient = rawgClient;
        this.mediaMapper = mediaMapper;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<MediaItemDto> getTrendingMovies() {
        return tmdbClient.getTrendingMovies().results().stream().map(mediaMapper::toDto).toList();
    }

    @Override
    public List<MediaItemDto> getTrendingTv() {
        return tmdbClient.getTrendingTv().results().stream().map(mediaMapper::toDto).toList();
    }

    @Override
    public List<GameItemDto> getPopularGames() {
        return rawgClient.getPopular().results().stream().map(gameMapper::toDto).toList();
    }
}
