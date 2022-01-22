package com.homework.bootcamp.controller.watchlist;


import com.homework.bootcamp.service.watchlist.Watchlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistCreateRequest {
    private Long memberId;
    private String watchlistName;
    private List<Long> moviesIds;

    public Watchlist convertToWatchlist() {
        return Watchlist.builder()
                .watchlistName(watchlistName)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
