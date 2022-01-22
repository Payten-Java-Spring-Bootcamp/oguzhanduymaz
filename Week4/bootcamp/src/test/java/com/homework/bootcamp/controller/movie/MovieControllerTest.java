package com.homework.bootcamp.controller.movie;

import com.homework.bootcamp.BaseIntegrationTest;
import com.homework.bootcamp.controller.actor.ActorRequest;
import com.homework.bootcamp.repository.actor.ActorEntity;
import com.homework.bootcamp.repository.actor.ActorJpaRepository;
import com.homework.bootcamp.repository.matching.MatchingEntity;
import com.homework.bootcamp.repository.matching.MatchingJpaRepository;
import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.movie.MovieJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class MovieControllerTest extends BaseIntegrationTest {

    @Autowired
    MovieJpaRepository movieJpaRepository;
    @Autowired
    ActorJpaRepository actorJpaRepository;
    @Autowired
    MatchingJpaRepository matchingJpaRepository;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_creat_movie_with_actors_in_db_and_new_actors() {
        //given
        MovieRequest request = new MovieRequest();
        request.setName("Test movie name");
        request.setGenre(Genre.COMEDY);
        request.setReleaseYear(2020);
        request.setDirector("testDirector");

        ActorRequest actorRequest = new ActorRequest();
        actorRequest.setName("Test actor name");
        actorRequest.setBirthDate(LocalDateTime.of(2020,2,3,3,3));
        ActorRequest actorRequest2 = new ActorRequest();
        actorRequest2.setName("Test actor name2");
        actorRequest2.setBirthDate(LocalDateTime.of(2020,2,4,4,4));

        request.setCast(List.of(actorRequest,actorRequest2));
//        request.setActorIds(List.of(123L,321L,456L));

        //when
        ResponseEntity<MovieCreateResponse> response = testRestTemplate.postForEntity("/movies", request, MovieCreateResponse.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        //validate movie
        Optional<MovieEntity> movie = movieJpaRepository.findById(response.getBody().getId());
        assertThat(movie).isPresent();
        assertThat(movie.get().getName()).isEqualTo("Test movie name");

        //validate actor
        List<ActorEntity> actors = actorJpaRepository.findAll();
        assertThat(actors)
                .hasSize(2)
                .extracting("name","birthDate")
                .contains(
                        tuple("Test actor name",LocalDateTime.of(2020,2,3,3,3)),
                        tuple("Test actor name2",LocalDateTime.of(2020,2,4,4,4))
                );
        //validate matchings
        List<MatchingEntity> matchings = matchingJpaRepository.findAll();
        assertThat(matchings).hasSize(2);
    }


    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_delete_movie() {
        //given TODO
        MovieRequest request = new MovieRequest();
        request.setName("Test movie name");
        request.setGenre(Genre.COMEDY);
        request.setReleaseYear(2020);
        request.setDirector("testDirector");
        request.setCast(new ArrayList<>());

        //when
        ResponseEntity<MovieCreateResponse> response = testRestTemplate.postForEntity("/movies", request, MovieCreateResponse.class);

        //then
        Optional<MovieEntity> movie = movieJpaRepository.findById(response.getBody().getId());
        assertThat(movie).isPresent();
        assertThat(movie.get().getName()).isEqualTo("Test movie name");

        //validate deleting
        testRestTemplate.delete("/movies/"+response.getBody().getId());


    }
}