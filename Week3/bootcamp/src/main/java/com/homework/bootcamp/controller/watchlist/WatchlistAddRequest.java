package com.homework.bootcamp.controller.watchlist;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class WatchlistAddRequest {
    private Long watchlistId;
    private List<Long> moviesIds;
    private LocalDateTime createdDate;
}
