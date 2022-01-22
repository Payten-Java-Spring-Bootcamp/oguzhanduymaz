package com.homework.bootcamp.controller.watchlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WatchlistAddRequest {
    private Long watchlistId;
    private List<Long> moviesIds;
}
