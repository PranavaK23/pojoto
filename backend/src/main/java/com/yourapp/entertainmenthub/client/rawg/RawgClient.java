package com.yourapp.entertainmenthub.client.rawg;

import com.yourapp.entertainmenthub.client.rawg.dto.RawgSearchResponse;

public interface RawgClient {
    RawgSearchResponse search(String query, int page);
    RawgSearchResponse getPopular();
}
