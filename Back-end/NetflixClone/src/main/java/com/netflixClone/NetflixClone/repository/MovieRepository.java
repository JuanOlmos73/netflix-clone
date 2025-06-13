package com.netflixClone.NetflixClone.repository;

import com.netflixClone.NetflixClone.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {}
