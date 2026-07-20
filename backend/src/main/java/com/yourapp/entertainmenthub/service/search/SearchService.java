package com.yourapp.entertainmenthub.service.search;

import com.yourapp.entertainmenthub.dto.response.SearchResultDto;

public interface SearchService {
    SearchResultDto search(String query, int page);
}
