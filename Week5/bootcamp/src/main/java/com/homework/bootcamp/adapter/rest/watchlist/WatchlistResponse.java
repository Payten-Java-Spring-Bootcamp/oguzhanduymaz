package com.homework.bootcamp.adapter.rest.watchlist;

import com.homework.bootcamp.domain.watchlist.Watchlist;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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
