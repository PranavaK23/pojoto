package com.yourapp.entertainmenthub.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "watchlist", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "media_type", "external_id"}))
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "media_type", nullable = false, length = 20)
    private String mediaType;

    @Column(name = "external_id", nullable = false)
    private Long externalId;

    @Column(nullable = false)
    private String title;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    protected Watchlist() {}

    public Watchlist(AppUser user, String mediaType, Long externalId, String title, String posterUrl) {
        this.user = user;
        this.mediaType = mediaType;
        this.externalId = externalId;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public Long getId() { return id; }
    public AppUser getUser() { return user; }
    public String getMediaType() { return mediaType; }
    public Long getExternalId() { return externalId; }
    public String getTitle() { return title; }
    public String getPosterUrl() { return posterUrl; }
    public Instant getCreatedAt() { return createdAt; }
}
