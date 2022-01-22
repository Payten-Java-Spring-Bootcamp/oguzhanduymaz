package com.homework.bootcamp.service.actor;

import com.homework.bootcamp.service.movie.Movie;

import java.util.List;

public interface ActorService {
    List<Movie> retrieveMovies(Long actorId);

    Long create(Actor actor);
}
