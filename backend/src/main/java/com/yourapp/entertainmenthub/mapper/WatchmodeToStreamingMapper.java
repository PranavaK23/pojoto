package com.yourapp.entertainmenthub.mapper;

import com.yourapp.entertainmenthub.client.watchmode.dto.WatchmodeSource;
import com.yourapp.entertainmenthub.dto.response.StreamingPlatformDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class WatchmodeToStreamingMapper {

    public List<StreamingPlatformDto> toDtoList(List<WatchmodeSource> sources) {
        return sources.stream()
                // De-dupe by platform name, preferring "sub" (subscription) listings when a platform offers multiple types
                .sorted(Comparator.comparing(s -> "sub".equals(s.type()) ? 0 : 1))
                .filter(distinctByName())
                .map(s -> new StreamingPlatformDto(s.name(), s.logo_100px(), s.type(), s.web_url()))
                .toList();
    }

    private java.util.function.Predicate<WatchmodeSource> distinctByName() {
        java.util.Set<String> seen = new java.util.HashSet<>();
        return s -> seen.add(s.name());
    }
}
