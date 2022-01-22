package com.homework.bootcamp.service.movie;

import com.homework.bootcamp.service.actor.Actor;

import java.util.List;

public interface MovieService {
    Long create(Movie movie, List<Actor> actors, List<Long> actorIds);
    Movie retrieve(Long id);
    List<Movie> retrieveAll();
    void delete(Long Id);
}
