package com.homework.bootcamp.controller.movie;

import com.homework.bootcamp.controller.actor.ActorResponse;
import com.homework.bootcamp.service.movie.Movie;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class MovieResponse {
    private String name;
    private Genre genre;
    private Integer releaseYear;
    private String director;
    private List<ActorResponse> castList;

    public static MovieResponse convertFrom(Movie movie) {
        List<ActorResponse> cast;
        if (CollectionUtils.isEmpty(movie.getCast())){
            cast = new ArrayList<>();
        }else{
            cast = movie.getCast().stream()
                    .map(ActorResponse::convertFromActor)
                    .collect(Collectors.toList());
        }
            return MovieResponse.builder()
                    .name(movie.getName())
                    .genre(movie.getGenre())
                    .releaseYear(movie.getReleaseYear())
                    .director(movie.getDirector())
                    .castList(cast)
                    .build();
    }
}
