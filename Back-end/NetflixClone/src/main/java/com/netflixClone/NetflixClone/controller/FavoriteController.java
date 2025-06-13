package com.netflixClone.NetflixClone.controller;

import com.netflixClone.NetflixClone.dto.response.MovieDto;
import com.netflixClone.NetflixClone.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{movieId}")
    public ResponseEntity<String> addFavorite(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long movieId
    ) {
        favoriteService.addFavorite(user.getUsername(), movieId);
        return ResponseEntity.ok("Agregado a favoritos");
    }

   @GetMapping
public ResponseEntity<List<MovieDto>> getFavorites(
        @AuthenticationPrincipal UserDetails user) {
    return ResponseEntity.ok(favoriteService.getFavoritesByUser(user.getUsername()));
}

@DeleteMapping("/{movieId}")
public ResponseEntity<String> removeFavorite(
        @AuthenticationPrincipal UserDetails user,
        @PathVariable Long movieId
) {
    favoriteService.removeFavorite(user.getUsername(), movieId);
    return ResponseEntity.ok("Eliminado de favoritos");
}
}
