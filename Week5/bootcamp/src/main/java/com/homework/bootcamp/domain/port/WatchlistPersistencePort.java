package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.adapter.jpa.watchlist.WatchlistEntity;

import java.util.List;

public interface WatchlistPersistencePort {
    WatchlistEntity save(WatchlistEntity watchlist, Long memberId, List<Long> movieId);

    WatchlistEntity retrieveById(Long watchlistId);

    List<WatchlistEntity> retrieveByMemberId(Long memberId);
}
