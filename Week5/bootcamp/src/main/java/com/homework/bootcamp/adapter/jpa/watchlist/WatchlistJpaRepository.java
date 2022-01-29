package com.homework.bootcamp.adapter.jpa.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistJpaRepository extends JpaRepository<WatchlistEntity, Long> {
    List<WatchlistEntity> findAllByMemberId(Long memberId);

}
