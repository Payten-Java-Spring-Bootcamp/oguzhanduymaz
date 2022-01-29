package com.homework.bootcamp.domain.movie.stub;

import com.homework.bootcamp.domain.actor.Actor;
import com.homework.bootcamp.domain.port.ActorPersistencePort;

import java.util.List;

public class StubActorPersistence implements ActorPersistencePort {
    @Override
    public Long save(Actor entity) {
        return null;
    }

    @Override
    public Actor retrieve(Long id) {
        return null;
    }

    @Override
    public List<Actor> retrieveAll() {
        return null;
    }

    @Override
    public void delete(Long Id) {

    }

    @Override
    public List<Actor> retrieve(List<Long> actorIds) {
        return null;
    }

    @Override
    public List<Actor> create(List<Actor> actors) {
        return null;
    }
}
