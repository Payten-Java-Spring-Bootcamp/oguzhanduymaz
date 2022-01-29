package com.homework.bootcamp.domain.movie.stub;

import com.homework.bootcamp.domain.actor.Actor;
import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.port.MatchingPersistencePort;

import java.util.List;

public class StubMatchingPersistence implements MatchingPersistencePort {
    @Override
    public void create(Movie movie, List<Actor> actors) {

    }
}
