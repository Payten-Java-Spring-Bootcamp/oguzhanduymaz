package com.homework.bootcamp.repository.movieWL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieWLJpaRepository extends JpaRepository<MovieWLEntity,Long> {

    @Query(value = "select WL from movieWLEntity as WL where WL.movieEntity.id = :movieId and WL.watchlistEntity.Id = :watchlistId")
    Optional<MovieWLEntity> findByMovieEntityIdAndAndWatchlistEntityId(Long movieId,Long watchlistId);

}
