package com.homework.bootcamp.repository.movie;

import java.util.List;

public interface MovieDao {

    MovieEntity save(MovieEntity entity);

    MovieEntity retrieve(Long id);

    List<MovieEntity> retrieveAll();

    void delete(Long Id);

    List<MovieEntity> retrieveByActorId(Long actorId);

    List<MovieEntity> findAllByIdIn(List<Long> movieIds);
}
