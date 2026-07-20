package com.yourapp.entertainmenthub.repository;

import com.yourapp.entertainmenthub.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findTop10ByUserIdOrderBySearchedAtDesc(Long userId);
}
