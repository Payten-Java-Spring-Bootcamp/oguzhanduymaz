package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.domain.movie.Movie;

public interface MovieCachePort {
    Movie retrieveMovie(Long movieId);

    void createMovie(Movie movie);
}
