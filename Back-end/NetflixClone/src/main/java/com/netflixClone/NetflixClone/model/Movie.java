package com.netflixClone.NetflixClone.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    private Long id; // Id externo TMDB

    private String title;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String description;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
