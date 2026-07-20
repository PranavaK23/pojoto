package com.yourapp.entertainmenthub.service.tvshow;

import com.yourapp.entertainmenthub.dto.response.MediaDetailDto;

public interface TvShowService {
    MediaDetailDto getTvShowDetail(long id);
}
