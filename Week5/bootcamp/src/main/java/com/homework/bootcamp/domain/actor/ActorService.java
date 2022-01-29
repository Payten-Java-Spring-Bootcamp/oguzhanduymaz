package com.homework.bootcamp.domain.actor;

import com.homework.bootcamp.domain.movie.Movie;

import java.util.List;

public interface ActorService {
    List<Movie> retrieveMovies(Long actorId);

    Long create(Actor actor);
}
