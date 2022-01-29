package com.homework.bootcamp.domain.movie;

import com.homework.bootcamp.adapter.rest.movie.Genre;
import com.homework.bootcamp.domain.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Movie {

    private Long id;
    private String name,director;
    private Genre genre;
    private Integer releaseYear;
    private List<Actor> cast;


}
