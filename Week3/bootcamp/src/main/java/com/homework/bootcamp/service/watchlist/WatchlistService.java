package com.homework.bootcamp.service.watchlist;

import java.util.List;

public interface WatchlistService {
    Watchlist create(Watchlist watchlist,Long memberId, List<Long> moviesIds);
    void addMovieToWatchlist(Long watchlistId, List<Long> movieIds);

}
