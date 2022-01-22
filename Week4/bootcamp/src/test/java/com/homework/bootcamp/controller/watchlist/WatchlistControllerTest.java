package com.homework.bootcamp.controller.watchlist;

import com.homework.bootcamp.BaseIntegrationTest;
import com.homework.bootcamp.controller.movie.Genre;
import com.homework.bootcamp.repository.member.MemberEntity;
import com.homework.bootcamp.repository.member.MemberJpaRepository;
import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.movie.MovieJpaRepository;
import com.homework.bootcamp.repository.movieWL.MovieWLEntity;
import com.homework.bootcamp.repository.movieWL.MovieWLJpaRepository;
import com.homework.bootcamp.repository.rate.RateJpaRepository;
import com.homework.bootcamp.repository.watchlist.WatchlistEntity;
import com.homework.bootcamp.repository.watchlist.WatchlistJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class WatchlistControllerTest extends BaseIntegrationTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    RateJpaRepository rateJpaRepository;

    @Autowired
    WatchlistJpaRepository watchlistJpaRepository;

    @Autowired
    MovieWLJpaRepository movieWLJpaRepository;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_Watchlist_with_movies() {
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

        MovieEntity movieEntity2 = new MovieEntity();
        movieEntity2.setName("Test movie name");
        movieEntity2.setGenre(Genre.COMEDY);
        movieEntity2.setReleaseYear(2020);
        movieEntity2.setDirector("testDirector");
        MovieEntity savedMovie2 = movieJpaRepository.save(movieEntity);

        WatchlistCreateRequest request =  new WatchlistCreateRequest();
        request.setMemberId(savedMember.getId());
        request.setWatchlistName("Test watchlist Name");
        request.setMoviesIds(List.of(savedMovie.getId(),savedMovie2.getId()));
        //when
        ResponseEntity<WatchlistCreateResponse> response = testRestTemplate.postForEntity("/watchlist", request, WatchlistCreateResponse.class);
        //then
        assertThat(response.getBody()).isNotNull();
        //validate Watchlist create
        Optional<WatchlistEntity> savedWatchlist = watchlistJpaRepository.findById(response.getBody().getId());
        assertThat(savedWatchlist).isPresent();
        assertThat(response.getBody().getId()).isEqualTo(savedWatchlist.get().getId());

    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_add_movie_to_empty_watchlist() {
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

        WatchlistEntity watchlistEntity = new WatchlistEntity();
        watchlistEntity.setWatchlistName("Test watchlist");
        watchlistEntity.setCreatedDate(LocalDateTime.now());
        watchlistEntity.setMember(savedMember);

        WatchlistEntity savedWL = watchlistJpaRepository.save(watchlistEntity);

        WatchlistAddRequest request =  new WatchlistAddRequest();
        request.setWatchlistId(savedWL.getId());
        request.setMoviesIds(List.of(savedMovie.getId()));
        //when
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/watchlist/add", request, Void.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        //Validate
        Optional<MovieWLEntity> savedWLMatching = movieWLJpaRepository.findByMovieEntityIdAndAndWatchlistEntityId(savedMovie.getId(),savedWL.getId());
        assertThat(savedWLMatching).isPresent();
        assertThat(savedWLMatching.get().getMovieEntity().getId()).isEqualTo(savedMovie.getId());
        assertThat(savedWLMatching.get().getWatchlistEntity().getId()).isEqualTo(savedWL.getId());


    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_get_watchlist() {
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

        WatchlistEntity watchlistEntity = new WatchlistEntity();
        watchlistEntity.setWatchlistName("Test watchlist");
        watchlistEntity.setCreatedDate(LocalDateTime.now());
        watchlistEntity.setMember(savedMember);
        WatchlistEntity savedWL = watchlistJpaRepository.save(watchlistEntity);

        MovieWLEntity movieWLEntity = new MovieWLEntity();
        movieWLEntity.setMovieEntity(savedMovie);
        movieWLEntity.setWatchlistEntity(savedWL);
        MovieWLEntity savedWLMatching = movieWLJpaRepository.save(movieWLEntity);

        //when
        ResponseEntity<WatchlistResponse[]> response = testRestTemplate.getForEntity("/watchlist/" + savedMember.getId(), WatchlistResponse[].class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        List<WatchlistResponse> responseList = Arrays.asList(response.getBody());
        assertThat(responseList).hasSize(1)
                .extracting("watchlistName").
                containsExactly("Test watchlist");

    }
}