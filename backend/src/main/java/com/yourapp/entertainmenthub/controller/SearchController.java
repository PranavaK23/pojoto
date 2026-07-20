package com.yourapp.entertainmenthub.controller;

import com.yourapp.entertainmenthub.dto.response.SearchResultDto;
import com.yourapp.entertainmenthub.service.search.SearchService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@CrossOrigin(origins = {"http://localhost:5173", "https://pojoto-sage.vercel.app"})
@Validated
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public SearchResultDto search(
            @RequestParam @NotBlank String q,
            @RequestParam(defaultValue = "1") int page
    ) {
        return searchService.search(q, page);
    }
}
