package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.domain.movie.Movie;

import java.util.List;

public interface MoviePersistencePort {

    Movie save(Movie entity);

    Movie retrieve(Long id);

    List<Movie> retrieveAll();

    void delete(Long Id);

    List<Movie> retrieveByActorId(Long actorId);

    List<Movie> findAllByIdIn(List<Long> movieIds);
}
