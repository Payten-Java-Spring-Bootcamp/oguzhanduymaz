package com.homework.bootcamp.service.watchlist;

import java.util.List;

public interface WatchlistService {
    Long create(Watchlist watchlist,Long memberId, List<Long> moviesIds);
    void addMovieToWatchlist(Long watchlistId, List<Long> movieIds);

    List<Watchlist> retrieveAll(Long memberId);
}
