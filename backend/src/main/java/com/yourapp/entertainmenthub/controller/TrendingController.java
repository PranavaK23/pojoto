package com.yourapp.entertainmenthub.controller;

import com.yourapp.entertainmenthub.dto.response.GameItemDto;
import com.yourapp.entertainmenthub.dto.response.MediaItemDto;
import com.yourapp.entertainmenthub.service.trending.TrendingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trending")
public class TrendingController {

    private final TrendingService trendingService;

    public TrendingController(TrendingService trendingService) {
        this.trendingService = trendingService;
    }

    @GetMapping("/movies")
    public List<MediaItemDto> trendingMovies() {
        return trendingService.getTrendingMovies();
    }

    @GetMapping("/tv")
    public List<MediaItemDto> trendingTv() {
        return trendingService.getTrendingTv();
    }

    @GetMapping("/games")
    public List<GameItemDto> popularGames() {
        return trendingService.getPopularGames();
    }
}
