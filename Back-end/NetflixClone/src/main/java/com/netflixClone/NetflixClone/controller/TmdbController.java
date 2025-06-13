package com.netflixClone.NetflixClone.controller;

import com.netflixClone.NetflixClone.service.TmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tmdb")
@RequiredArgsConstructor
public class TmdbController {

    private final TmdbService tmdbService;

    @GetMapping("/now-playing")
    public ResponseEntity<String> getNowPlaying() {
        return ResponseEntity.ok(tmdbService.fetchNowPlaying());
    }

    @GetMapping("/popular")
    public ResponseEntity<String> getPopular() {
        return ResponseEntity.ok(tmdbService.fetchPopular());
    }

    @GetMapping("/top-rated")
    public ResponseEntity<String> getTopRated() {
        return ResponseEntity.ok(tmdbService.fetchTopRated());
    }
}
