package com.yourapp.entertainmenthub.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(nullable = false)
    private String query;

    @Column(name = "searched_at", nullable = false)
    private Instant searchedAt = Instant.now();

    protected SearchHistory() {}

    public SearchHistory(AppUser user, String query) {
        this.user = user;
        this.query = query;
    }

    public Long getId() { return id; }
    public AppUser getUser() { return user; }
    public String getQuery() { return query; }
    public Instant getSearchedAt() { return searchedAt; }
}
