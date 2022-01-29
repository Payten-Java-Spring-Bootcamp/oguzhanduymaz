package com.homework.bootcamp.domain.watchlist;

import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.adapter.jpa.watchlist.WatchlistEntity;
import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
public class Watchlist {
    private Member owner;
    private String watchlistName;
    private List<Movie> movies;
    private LocalDateTime createdDate;

    public static Watchlist convertFrom(WatchlistEntity saved) {
        Watchlist convert = Watchlist.builder()
                .owner(Member.convertFrom(saved.getMember()))
                .watchlistName(saved.getWatchlistName())
                .createdDate(saved.getCreatedDate())
                .build();

        if (saved.getMovies() != null){
            convert.setMovies(saved.getMovies().stream().map(e -> e.getMovieEntity().toModel()).collect(Collectors.toList()));
        }
        return convert;
    }

    public WatchlistEntity convertToWatchlistEntity() {
        WatchlistEntity entity = new WatchlistEntity();
        entity.setMember(owner.convertToMemberEntity());
        entity.setWatchlistName(watchlistName);
        entity.setCreatedDate(createdDate);

        return entity;
    }
}
