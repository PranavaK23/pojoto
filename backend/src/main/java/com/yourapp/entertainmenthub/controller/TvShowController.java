package com.yourapp.entertainmenthub.controller;

import com.yourapp.entertainmenthub.dto.response.MediaDetailDto;
import com.yourapp.entertainmenthub.service.tvshow.TvShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tv")
public class TvShowController {

    private final TvShowService tvShowService;

    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping("/{id}")
    public MediaDetailDto getTvShow(@PathVariable long id) {
        return tvShowService.getTvShowDetail(id);
    }
}
