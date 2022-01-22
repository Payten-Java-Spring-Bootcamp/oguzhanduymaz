package com.homework.bootcamp.controller.watchlist;

import com.homework.bootcamp.service.movie.Movie;
import com.homework.bootcamp.service.watchlist.Watchlist;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class WatchListResponse {
    private String watchlistName;
    private List<Movie> movies;
    private LocalDateTime createdDate;

    public static WatchListResponse convertFrom(Watchlist watchlist) {
        return  WatchListResponse.builder()
                .watchlistName(watchlist.getWatchlistName())
                .createdDate(watchlist.getCreatedDate())
                .movies(watchlist.getMovies())
                .build();
    }
}
