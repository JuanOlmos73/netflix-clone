package com.netflixClone.NetflixClone.model;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFavoriteId implements Serializable {
    private Long user;
    private Long movie;

    // Constructor personalizado (Lombok no lo generará debido a @NoArgsConstructor)
    public UserFavoriteId(Long user, Long movie) {
        this.user = user;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFavoriteId that = (UserFavoriteId) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie);
    }
}