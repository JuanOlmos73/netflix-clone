package com.netflixClone.NetflixClone.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String overview;
    private String posterPath;
}
