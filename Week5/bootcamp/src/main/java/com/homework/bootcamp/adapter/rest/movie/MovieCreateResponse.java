package com.homework.bootcamp.adapter.rest.movie;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MovieCreateResponse {

    private Long id;

    public static MovieCreateResponse convertToMovieResponse(Long id) {
        return MovieCreateResponse.builder()
                .id(id)
                .build();
    }
}
