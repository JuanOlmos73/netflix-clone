package com.netflixClone.NetflixClone.service;

import com.netflixClone.NetflixClone.dto.response.MovieDto;
import com.netflixClone.NetflixClone.exception.ConflictException;
import com.netflixClone.NetflixClone.exception.NotFoundException;
import com.netflixClone.NetflixClone.model.Movie;
import com.netflixClone.NetflixClone.model.User;
import com.netflixClone.NetflixClone.model.UserFavorite;
import com.netflixClone.NetflixClone.model.UserFavoriteId;
import com.netflixClone.NetflixClone.repository.MovieRepository;
import com.netflixClone.NetflixClone.repository.UserFavoriteRepository;
import com.netflixClone.NetflixClone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserFavoriteRepository favoriteRepository;

    @Transactional
    public void addFavorite(String userEmail, Long movieId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        if (favoriteRepository.existsById(new UserFavoriteId(user.getId(), movieId))) {
            throw new ConflictException("Ya está en favoritos");
        }

        Movie movie = movieRepository.findById(movieId).orElseGet(() -> {
            Movie newMovie = new Movie();
            newMovie.setId(movieId);
            newMovie.setTitle("Cargando...");
            newMovie.setDescription("");
            newMovie.setPosterPath("");
            newMovie.setCreatedAt(LocalDateTime.now());
            return movieRepository.save(newMovie);
        });

        UserFavorite fav = UserFavorite.builder()
                .user(user)
                .movie(movie)
                .build();
        
        favoriteRepository.save(fav);
    }

    public List<MovieDto> getFavoritesByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        return favoriteRepository.findByUser(user).stream()
                .map(fav -> convertToDto(fav.getMovie()))
                .collect(Collectors.toList());
    }

    private MovieDto convertToDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getPosterPath()
        );
    }

  @Transactional
public void removeFavorite(String userEmail, Long movieId) {
    User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    favoriteRepository.deleteByUserIdAndMovieId(user.getId(), movieId);
}
}