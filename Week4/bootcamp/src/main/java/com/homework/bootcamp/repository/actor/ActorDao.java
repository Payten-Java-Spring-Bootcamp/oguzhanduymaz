package com.homework.bootcamp.repository.actor;

import java.util.List;

public interface ActorDao {
    Long save(ActorEntity entity);

    ActorEntity retrieve(Long id);

    List<ActorEntity> retrieveAll();

    void delete(Long Id);

    List<ActorEntity> retrieve(List<Long> actorIds);

    List<ActorEntity> create(List<ActorEntity> actors);
}
