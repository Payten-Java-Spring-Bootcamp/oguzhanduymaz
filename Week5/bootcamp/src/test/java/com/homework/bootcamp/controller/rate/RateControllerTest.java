package com.homework.bootcamp.controller.rate;

import com.homework.bootcamp.BaseIntegrationTest;
import com.homework.bootcamp.adapter.rest.movie.Genre;
import com.homework.bootcamp.adapter.rest.rate.RateRequest;
import com.homework.bootcamp.adapter.rest.rate.RateResponse;
import com.homework.bootcamp.adapter.jpa.member.MemberEntity;
import com.homework.bootcamp.adapter.jpa.member.MemberJpaRepository;
import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.adapter.jpa.movie.MovieJpaRepository;
import com.homework.bootcamp.adapter.jpa.rate.RateEntity;
import com.homework.bootcamp.adapter.jpa.rate.RateJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class RateControllerTest extends BaseIntegrationTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    RateJpaRepository rateJpaRepository;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void rate_movie() {
        //given
        MemberEntity entity = new MemberEntity();
        entity.setName("Test member");
        MemberEntity savedMember = memberJpaRepository.save(entity);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName("Test movie name");
        movieEntity.setGenre(Genre.COMEDY);
        movieEntity.setReleaseYear(2020);
        movieEntity.setDirector("testDirector");
        MovieEntity savedMovie = movieJpaRepository.save(movieEntity);

        RateRequest request = new RateRequest();
        request.setMemberId(savedMember.getId());
        request.setMovieId(savedMovie.getId());
        request.setPoint(10);

        //when
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/rates", request, Void.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void retrieve_rates_by_movie() {
        //given
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName("Test member");
        MemberEntity savedMember = memberJpaRepository.save(memberEntity);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName("Test movie name");
        movieEntity.setGenre(Genre.COMEDY);
        movieEntity.setReleaseYear(2020);
        movieEntity.setDirector("testDirector");
        MovieEntity savedMovie = movieJpaRepository.save(movieEntity);

        LocalDateTime now = LocalDateTime.now();
        RateEntity rate = new RateEntity();
        rate.setMember(savedMember);
        rate.setMovie(savedMovie);
        rate.setPoint(8);
        rate.setCreatedDate(now);
        rateJpaRepository.save(rate);

        RateEntity rate2 = new RateEntity();
        rate2.setMember(savedMember);
        rate2.setMovie(savedMovie);
        rate2.setPoint(9);
        rate2.setCreatedDate(now);
        rateJpaRepository.save(rate2);

        //when
        ResponseEntity<RateResponse[]> response = testRestTemplate.getForEntity("/rates/"+savedMovie.getId(),RateResponse[].class);
        //then
        assertThat(response.getBody()).isNotNull();
        List<RateResponse> responseList = List.of(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseList)
                .hasSize(2)
                .extracting("memberId","movieId","point")
                .contains(
                        tuple(savedMember.getId(),savedMovie.getId(),8),
                        tuple(savedMember.getId(),savedMovie.getId(),9)
                );
    }
}