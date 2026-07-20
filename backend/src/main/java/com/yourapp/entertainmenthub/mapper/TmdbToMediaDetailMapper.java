package com.yourapp.entertainmenthub.mapper;

import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbCrewMember;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbMovieDetailResponse;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbTvDetailResponse;
import com.yourapp.entertainmenthub.client.tmdb.dto.TmdbVideo;
import com.yourapp.entertainmenthub.dto.response.CastMemberDto;
import com.yourapp.entertainmenthub.dto.response.MediaDetailDto;
import com.yourapp.entertainmenthub.dto.response.MediaItemDto;
import com.yourapp.entertainmenthub.dto.response.StreamingPlatformDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Builds the detail-page DTO for both movies and TV shows. Streaming platforms are
 * passed in already-mapped (from WatchmodeClient) rather than looked up here, keeping
 * this mapper focused on TMDb's shape only — see docs/ARCHITECTURE.md on provider isolation.
 */
@Component
public class TmdbToMediaDetailMapper {

    private final TmdbToMediaItemMapper mediaItemMapper;

    public TmdbToMediaDetailMapper(TmdbToMediaItemMapper mediaItemMapper) {
        this.mediaItemMapper = mediaItemMapper;
    }

    public MediaDetailDto fromMovie(TmdbMovieDetailResponse r, List<StreamingPlatformDto> streaming) {
        List<CastMemberDto> cast = r.credits() == null ? List.of() :
                r.credits().cast().stream()
                        .sorted((a, b) -> Integer.compare(a.order(), b.order()))
                        .limit(12)
                        .map(c -> new CastMemberDto(c.id(), c.name(), c.character(), mediaItemMapper.buildImageUrl(c.profile_path(), "w185")))
                        .toList();

        String director = r.credits() == null ? null :
                r.credits().crew().stream()
                        .filter(c -> "Director".equals(c.job()))
                        .map(TmdbCrewMember::name)
                        .findFirst().orElse(null);

        List<MediaItemDto> similar = r.similar() == null ? List.of() :
                r.similar().results().stream()
                        .limit(12)
                        .map(mediaItemMapper::toDto)
                        .toList();

        return new MediaDetailDto(
                r.id(), "movie", r.title(), r.overview(),
                mediaItemMapper.buildImageUrl(r.poster_path(), "w500"),
                mediaItemMapper.buildImageUrl(r.backdrop_path(), "original"),
                r.genres() == null ? List.of() : r.genres().stream().map(g -> g.name()).toList(),
                r.runtime(), null, null, null, null,
                r.release_date(), r.vote_average(),
                cast, director,
                resolveTrailer(r.videos() == null ? List.of() : r.videos().results()),
                streaming, similar
        );
    }

    public MediaDetailDto fromTv(TmdbTvDetailResponse r, List<StreamingPlatformDto> streaming) {
        List<CastMemberDto> cast = r.credits() == null ? List.of() :
                r.credits().cast().stream()
                        .sorted((a, b) -> Integer.compare(a.order(), b.order()))
                        .limit(12)
                        .map(c -> new CastMemberDto(c.id(), c.name(), c.character(), mediaItemMapper.buildImageUrl(c.profile_path(), "w185")))
                        .toList();

        String network = r.networks() == null || r.networks().isEmpty() ? null : r.networks().get(0).name();

        return new MediaDetailDto(
                r.id(), "tv", r.name(), r.overview(),
                mediaItemMapper.buildImageUrl(r.poster_path(), "w500"),
                mediaItemMapper.buildImageUrl(r.backdrop_path(), "original"),
                r.genres() == null ? List.of() : r.genres().stream().map(g -> g.name()).toList(),
                null, network, r.number_of_seasons(), r.number_of_episodes(), r.status(),
                r.first_air_date(), r.vote_average(),
                cast, null,
                resolveTrailer(r.videos() == null ? List.of() : r.videos().results()),
                streaming, List.of() // TMDb TV detail doesn't include "similar" in this call; add a follow-up call if needed
        );
    }

    private String resolveTrailer(List<TmdbVideo> videos) {
        return videos.stream()
                .filter(v -> "YouTube".equals(v.site()) && "Trailer".equals(v.type()) && v.official())
                .findFirst()
                .or(() -> videos.stream().filter(v -> "YouTube".equals(v.site())).findFirst())
                .map(v -> "https://www.youtube.com/watch?v=" + v.key())
                .orElse(null);
    }
}
