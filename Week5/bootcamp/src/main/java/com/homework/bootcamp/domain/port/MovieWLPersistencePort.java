package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.adapter.jpa.movieWL.MovieWLEntity;

import java.util.List;

public interface MovieWLPersistencePort {
    void saveAll(List<MovieWLEntity> wlEntities);
}
