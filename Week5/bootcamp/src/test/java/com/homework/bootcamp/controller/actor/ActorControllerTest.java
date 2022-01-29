package com.homework.bootcamp.controller.actor;

import com.homework.bootcamp.BaseIntegrationTest;
import com.homework.bootcamp.adapter.rest.actor.ActorCreateRequest;
import com.homework.bootcamp.adapter.rest.actor.ActorCreateResponse;
import com.homework.bootcamp.adapter.rest.movie.Genre;
import com.homework.bootcamp.adapter.rest.movie.MovieResponse;
import com.homework.bootcamp.adapter.jpa.actor.ActorEntity;
import com.homework.bootcamp.adapter.jpa.actor.ActorJpaRepository;
import com.homework.bootcamp.adapter.jpa.matching.MatchingEntity;
import com.homework.bootcamp.adapter.jpa.matching.MatchingJpaRepository;
import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.adapter.jpa.movie.MovieJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class ActorControllerTest extends BaseIntegrationTest {

    @Autowired
    ActorJpaRepository actorJpaRepository;

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    MatchingJpaRepository matchingJpaRepository;


    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_actor() {
        //given
        ActorCreateRequest request = new ActorCreateRequest();
        request.setName("Test Actor");
        request.setBirthDate(LocalDateTime.of(2020,1,1,1,1));
        //when
        ResponseEntity<ActorCreateResponse> response = testRestTemplate.postForEntity("/actors", request, ActorCreateResponse.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isNotNull();

        //validate
        Optional<ActorEntity> actorEntity = actorJpaRepository.findById(response.getBody().getId());
        assertThat(actorEntity).isPresent();

    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_get_movies_by_actor(){
        //given
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName("Test movie name");
        movieEntity.setGenre(Genre.COMEDY);
        movieEntity.setReleaseYear(2020);
        movieEntity.setDirector("testDirector");


        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName("Test Actor");
        actorEntity.setBirthDate(LocalDateTime.of(2020,1,1,1,1));
        ActorEntity saved = actorJpaRepository.save(actorEntity);

        MatchingEntity matchingEntity = new MatchingEntity();
        matchingEntity.setActorEntity(actorEntity);
        matchingEntity.setMovieEntity(movieEntity);

        movieJpaRepository.save(movieEntity);
        matchingJpaRepository.save(matchingEntity);
        //when
        ResponseEntity<MovieResponse[]> response = testRestTemplate.getForEntity("/actors/" + saved.getId() + "/movies", MovieResponse[].class);
        //then
        assertThat(response.getBody()).isNotNull();
        List<MovieResponse> listOfResponse = List.of(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listOfResponse)
                .hasSize(1)
                .extracting("name","genre","releaseYear","director")
                .contains(tuple("Test movie name",Genre.COMEDY,2020,"testDirector"));
    }
}