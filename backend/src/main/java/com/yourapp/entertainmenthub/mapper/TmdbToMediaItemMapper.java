package com.yourapp.entertainmenthub.mapper;

import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbSearchResult;
import com.yourapp.entertainmenthub.config.ExternalApiProperties;
import com.yourapp.entertainmenthub.dto.response.MediaItemDto;
import org.springframework.stereotype.Component;

@Component
public class TmdbToMediaItemMapper {

    private final ExternalApiProperties props;

    public TmdbToMediaItemMapper(ExternalApiProperties props) {
        this.props = props;
    }

    public MediaItemDto toDto(TmdbSearchResult r) {
        boolean isMovie = "movie".equals(r.media_type());
        return new MediaItemDto(
                r.id(),
                r.media_type(),
                isMovie ? r.title() : r.name(),
                r.overview(),
                buildImageUrl(r.poster_path(), "w342"),
                buildImageUrl(r.backdrop_path(), "w780"),
                r.vote_average(),
                isMovie ? r.release_date() : r.first_air_date(),
                TmdbGenreLookup.resolve(r.genre_ids())
        );
    }

    public String buildImageUrl(String path, String size) {
        if (path == null || path.isBlank()) return null;
        return props.tmdb().imageBaseUrl() + "/" + size + path;
    }
}
