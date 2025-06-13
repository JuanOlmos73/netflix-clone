package com.netflixClone.NetflixClone.repository;

import com.netflixClone.NetflixClone.model.User;
import com.netflixClone.NetflixClone.model.UserFavorite;
import com.netflixClone.NetflixClone.model.UserFavoriteId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, UserFavoriteId> {

    // Obtener todos los favoritos de un usuario
    List<UserFavorite> findByUser(User user);

    // Verificar si ya existe un favorito
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
           "FROM UserFavorite f " +
           "WHERE f.user.email = :email AND f.movie.id = :movieId")
    boolean existsByUserEmailAndMovieId(@Param("email") String email, @Param("movieId") Long movieId);

   @Modifying
@Transactional
@Query("DELETE FROM UserFavorite f WHERE f.user.id = :userId AND f.movie.id = :movieId")
void deleteByUserIdAndMovieId(@Param("userId") Long userId, @Param("movieId") Long movieId);
}
