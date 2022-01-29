package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.domain.actor.Actor;
import com.homework.bootcamp.domain.movie.Movie;

import java.util.List;

public interface MatchingPersistencePort {
    void create(Movie movie, List<Actor> actors);
}
