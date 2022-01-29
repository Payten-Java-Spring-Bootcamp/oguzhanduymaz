package com.homework.bootcamp.adapter.rest.watchlist;

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
