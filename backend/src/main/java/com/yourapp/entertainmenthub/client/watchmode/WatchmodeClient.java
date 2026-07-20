package com.yourapp.entertainmenthub.client.watchmode;

import com.yourapp.entertainmenthub.client.watchmode.dto.WatchmodeSource;

import java.util.List;

public interface WatchmodeClient {
    /**
     * Watchmode identifies titles by its own id, resolved via TMDb id lookup.
     * tmdbType is "movie" or "tv".
     */
    List<WatchmodeSource> getStreamingSources(long tmdbId, String tmdbType);
}
