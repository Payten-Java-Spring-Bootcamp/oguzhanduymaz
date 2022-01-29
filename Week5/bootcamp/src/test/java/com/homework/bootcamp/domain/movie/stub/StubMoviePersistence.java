package com.homework.bootcamp.domain.movie.stub;

import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.port.MoviePersistencePort;

import java.util.List;

public class StubMoviePersistence implements MoviePersistencePort {
    public int interaction = 0;
    @Override
    public Movie save(Movie entity) {
        interaction++;
        return null;
    }

    @Override
    public Movie retrieve(Long id) {
        interaction++;
        if (id == 2){
            return Movie.builder()
                    .id(2L)
                    .director("Test Director")
                    .build();
        }
        return null;
    }

    @Override
    public List<Movie> retrieveAll() {
        interaction++;
        return null;
    }

    @Override
    public void delete(Long Id) {
        interaction++;

    }

    @Override
    public List<Movie> retrieveByActorId(Long actorId) {
        interaction++;
        return null;
    }

    @Override
    public List<Movie> findAllByIdIn(List<Long> movieIds) {
        interaction++;
        return null;
    }
}
