package com.homework.bootcamp.service.movie;

import com.homework.bootcamp.controller.movie.Genre;
import com.homework.bootcamp.repository.actor.ActorEntity;
import com.homework.bootcamp.repository.matching.MatchingEntity;
import com.homework.bootcamp.service.actor.Actor;
import com.homework.bootcamp.repository.movie.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Movie {

    private String name,director;
    private Genre genre;
    private Integer releaseYear;
    private List<Actor> cast;

    public static Movie convertFrom(MovieEntity movie) {
        List<ActorEntity> collect = movie
                .getMatchings().stream()
                .map(MatchingEntity::getActorEntity)
                .collect(Collectors.toList());
        return Movie.builder()
                .name(movie.getName())
                .director(movie.getDirector())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .cast(collect.stream().map(Actor::convertFrom).collect(Collectors.toList()))
                .build();
    }

    public MovieEntity convertToMovieEntity() {
        MovieEntity movie = new MovieEntity();
        movie.setName(name);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        return movie;
    }

}
