package com.homework.bootcamp.domain.movie;

import com.homework.bootcamp.domain.movie.stub.StubActorPersistence;
import com.homework.bootcamp.domain.movie.stub.StubMatchingPersistence;
import com.homework.bootcamp.domain.movie.stub.StubMovieCache;
import com.homework.bootcamp.domain.movie.stub.StubMoviePersistence;
import com.homework.bootcamp.domain.port.ActorPersistencePort;
import com.homework.bootcamp.domain.port.MatchingPersistencePort;
import com.homework.bootcamp.domain.port.MovieCachePort;
import com.homework.bootcamp.domain.port.MoviePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class MovieServiceTestWithStub {

    MovieService movieService;

    MoviePersistencePort moviePersistencePort;
    ActorPersistencePort actorPersistencePort;
    MatchingPersistencePort matchingPersistencePort;
    MovieCachePort movieCachePort;

    @BeforeEach
    void setUp() {
        moviePersistencePort = new StubMoviePersistence();
        actorPersistencePort = new StubActorPersistence();
        matchingPersistencePort = new StubMatchingPersistence();
        movieCachePort = new StubMovieCache();

        movieService = new MovieService(
                moviePersistencePort,
                actorPersistencePort,
                matchingPersistencePort,
                movieCachePort
        );
    }

    @Test
    void should_retrieve_movie_when_cache_is_exist(){
        //when
        Movie movie = movieService.retrieve(1L);
        //then
        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(1L);
        assertThat(movie.getDirector()).isEqualTo("Test Director");

        assertThat(((StubMoviePersistence)(moviePersistencePort)).interaction).isZero();
        assertThat(((StubMovieCache)(movieCachePort)).interaction).isEqualTo(1);
    }

    @Test
    void should_retrieve_movie_when_cache_empty(){
        //when
        Movie movie = movieService.retrieve(2L);

        //then
        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(2L);
        assertThat(movie.getDirector()).isEqualTo("Test Director");

        assertThat(((StubMoviePersistence)(moviePersistencePort)).interaction).isZero();
        assertThat(((StubMovieCache)(movieCachePort)).interaction).isEqualTo(2);
    }
}