package com.homework.bootcamp.controller.watchlist;

import com.homework.bootcamp.service.watchlist.Watchlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WatchlistCreateResponse {
    private Long id;

    public static WatchlistCreateResponse convertFrom(Long id) {
        return WatchlistCreateResponse.builder()
                .id(id)
                .build();
    }
}
