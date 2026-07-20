package com.yourapp.entertainmenthub.service.trending;

import com.yourapp.entertainmenthub.dto.response.GameItemDto;
import com.yourapp.entertainmenthub.dto.response.MediaItemDto;

import java.util.List;

public interface TrendingService {
    List<MediaItemDto> getTrendingMovies();
    List<MediaItemDto> getTrendingTv();
    List<GameItemDto> getPopularGames();
}
