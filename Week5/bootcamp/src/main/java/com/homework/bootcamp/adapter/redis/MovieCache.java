package com.homework.bootcamp.adapter.redis;

import com.homework.bootcamp.adapter.rest.movie.Genre;
import com.homework.bootcamp.domain.movie.Movie;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieCache {
    private Long id;
    private String name,director;
    private Genre genre;
    private Integer releaseYear;

    public static MovieCache from(Movie movie){
        return MovieCache.builder()
                .id(movie.getId())
                .name(movie.getName())
                .director(movie.getDirector())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .build();
    }

    public Movie toModel() {
        return Movie.builder()
                .id(id)
                .name(name)
                .director(director)
                .genre(genre)
                .releaseYear(releaseYear)
                .build();
    }
}
