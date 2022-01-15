package com.homework.bootcamp.controller.watchlist;


import com.homework.bootcamp.service.watchlist.Watchlist;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class WatchlistCreateRequest {
    private Long memberId;
    private String watchlistName;
    private List<Long> moviesIds;
    private LocalDateTime createdDate;

    public Watchlist convertToWatchlist() {
        return Watchlist.builder()
                .watchlistName(watchlistName)
                .createdDate(createdDate)
                .build();
    }
}
