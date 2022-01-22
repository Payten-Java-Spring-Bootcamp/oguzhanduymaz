package com.homework.bootcamp.repository.watchlist;

import com.homework.bootcamp.repository.movieWL.MovieWLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistJpaRepository extends JpaRepository<WatchlistEntity, Long> {
    List<WatchlistEntity> findAllByMemberId(Long memberId);

}
