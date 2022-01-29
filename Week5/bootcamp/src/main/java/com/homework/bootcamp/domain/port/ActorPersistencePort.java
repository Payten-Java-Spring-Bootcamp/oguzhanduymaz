package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.adapter.jpa.actor.ActorEntity;
import com.homework.bootcamp.domain.actor.Actor;

import java.util.List;

public interface ActorPersistencePort {
    Long save(Actor entity);

    Actor retrieve(Long id);

    List<Actor> retrieveAll();

    void delete(Long Id);

    List<Actor> retrieve(List<Long> actorIds);

    List<Actor> create(List<Actor> actors);
}
