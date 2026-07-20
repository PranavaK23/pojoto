package com.yourapp.entertainmenthub.service.movie;

import com.yourapp.entertainmenthub.dto.response.MediaDetailDto;

public interface MovieService {
    MediaDetailDto getMovieDetail(long id);
}
