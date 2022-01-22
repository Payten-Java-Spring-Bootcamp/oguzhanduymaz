package com.homework.bootcamp.repository.watchlist;

import java.util.List;

public interface WatchlistDao {
    WatchlistEntity save(WatchlistEntity watchlist,Long memberId, List<Long> movieId);

    WatchlistEntity retrieveById(Long watchlistId);

    List<WatchlistEntity> retrieveByMemberId(Long memberId);
}
