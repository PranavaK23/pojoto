package com.yourapp.entertainmenthub.controller;

import com.yourapp.entertainmenthub.dto.response.MediaDetailDto;
import com.yourapp.entertainmenthub.service.movie.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public MediaDetailDto getMovie(@PathVariable long id) {
        return movieService.getMovieDetail(id);
    }
}
