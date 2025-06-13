
package com.netflixClone.NetflixClone.service;

import com.netflixClone.NetflixClone.dto.response.MovieDto;
import com.netflixClone.NetflixClone.exception.NotFoundException;
import com.netflixClone.NetflixClone.model.Movie;
import com.netflixClone.NetflixClone.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repo;

    public List<MovieDto> findAll() {
        return repo.findAll().stream()
                   .map(m -> new MovieDto(
                       m.getId(),
                       m.getTitle(),
                       m.getDescription(),
                       m.getPosterPath()
                   ))
                   .collect(Collectors.toList());
    }

    public MovieDto findById(Long id) {
        Movie m = repo.findById(id)
                      .orElseThrow(() -> new NotFoundException("Movie no encontrada"));
        return new MovieDto(
            m.getId(),
            m.getTitle(),
            m.getDescription(),
            m.getPosterPath()
        );
    }

    public MovieDto create(MovieDto dto) {
        Movie m = Movie.builder()
                       .id(dto.getId())          // in case creating from TMDB
                       .title(dto.getTitle())
                       .description(dto.getOverview())
                       .posterPath(dto.getPosterPath())
                       .createdAt(LocalDateTime.now())
                       .build();
        m = repo.save(m);
        return new MovieDto(
            m.getId(),
            m.getTitle(),
            m.getDescription(),
            m.getPosterPath()
        );
    }

    public MovieDto update(Long id, MovieDto dto) {
        Movie m = repo.findById(id)
                      .orElseThrow(() -> new NotFoundException("Movie no encontrada"));
        m.setTitle(dto.getTitle());
        m.setDescription(dto.getOverview());
        m.setPosterPath(dto.getPosterPath());
        repo.save(m);
        return new MovieDto(
            m.getId(),
            m.getTitle(),
            m.getDescription(),
            m.getPosterPath()
        );
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Movie findMovieById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new NotFoundException("Película no encontrada con ID: " + id));
    }

    public Movie findOrCreateMovie(Long id) {
        return repo.findById(id).orElseGet(() -> {
            Movie m = new Movie();
            m.setId(id);
            m.setTitle("Título temporal");
            m.setDescription("Descripción vacía");
            m.setPosterPath("");
            m.setCreatedAt(LocalDateTime.now());
            return repo.save(m);
        });
    }
}
