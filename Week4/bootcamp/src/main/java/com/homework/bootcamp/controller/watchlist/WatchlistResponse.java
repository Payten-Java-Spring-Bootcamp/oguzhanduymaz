package com.homework.bootcamp.controller.watchlist;

import com.homework.bootcamp.service.movie.Movie;
import com.homework.bootcamp.service.watchlist.Watchlist;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class WatchlistResponse {
    private String watchlistName;
    private LocalDateTime createdDate;

    public static WatchlistResponse convertFrom(Watchlist watchlist) {
        return  WatchlistResponse.builder()
                .watchlistName(watchlist.getWatchlistName())
                .createdDate(watchlist.getCreatedDate())
                .build();
    }
}
