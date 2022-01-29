package com.homework.bootcamp.domain.movie.stub;

import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.port.MovieCachePort;

public class StubMovieCache implements MovieCachePort {
    public Integer interaction = 0;
    @Override
    public Movie retrieveMovie(Long movieId) {
        interaction++;
        if (movieId == 1){
            return Movie.builder()
                    .id(1L)
                    .director("Test Director")
                    .build();
        }
        return null;
    }

    @Override
    public void createMovie(Movie movie) {
        interaction++;
    }
}
