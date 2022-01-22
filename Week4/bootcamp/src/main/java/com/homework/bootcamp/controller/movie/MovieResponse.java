package com.homework.bootcamp.controller.movie;

import com.homework.bootcamp.service.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MovieResponse {
    private String name;
    private Genre genre;
    private Integer releaseYear;
    private String director;

    public static MovieResponse convertFrom(Movie movie) {
            return MovieResponse.builder()
                    .name(movie.getName())
                    .genre(movie.getGenre())
                    .releaseYear(movie.getReleaseYear())
                    .director(movie.getDirector())
                    .build();
    }
}
