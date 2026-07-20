package com.yourapp.entertainmenthub.mapper;

import com.yourapp.entertainmenthub.client.rawg.dto.RawgGameResult;
import com.yourapp.entertainmenthub.dto.response.GameItemDto;
import org.springframework.stereotype.Component;

@Component
public class RawgToGameItemMapper {

    public GameItemDto toDto(RawgGameResult r) {
        return new GameItemDto(
                r.id(),
                r.name(),
                r.background_image(),
                r.rating(),
                r.released(),
                r.platforms() == null ? java.util.List.of() :
                        r.platforms().stream().map(p -> p.platform().name()).toList()
        );
    }
}
