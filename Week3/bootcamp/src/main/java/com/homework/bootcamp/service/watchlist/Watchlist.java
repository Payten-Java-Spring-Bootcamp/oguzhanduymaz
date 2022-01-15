package com.homework.bootcamp.service.watchlist;

import com.homework.bootcamp.repository.watchlist.WatchlistEntity;
import com.homework.bootcamp.service.member.Member;
import com.homework.bootcamp.service.movie.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
public class Watchlist {
    private Member owner;
    private String watchlistName;
    private List<Movie> movies;
    private LocalDateTime createdDate;

    public static Watchlist convertFrom(WatchlistEntity saved) {
        return Watchlist.builder()
                .owner(Member.convertFrom(saved.getOwner()))
                .watchlistName(saved.getWatchlistName())
                .createdDate(saved.getCreatedDate())
                .build();
    }

    public WatchlistEntity convertToWatchlistEntity() {
        WatchlistEntity entity = new WatchlistEntity();
        entity.setOwner(owner.convertToMemberEntity());
        entity.setWatchlistName(watchlistName);
        entity.setCreatedDate(createdDate);

        return entity;
    }
}
