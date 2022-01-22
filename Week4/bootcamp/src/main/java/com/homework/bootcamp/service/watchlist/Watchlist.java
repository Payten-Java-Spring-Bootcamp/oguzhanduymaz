package com.homework.bootcamp.service.watchlist;

import com.homework.bootcamp.repository.movieWL.MovieWLEntity;
import com.homework.bootcamp.repository.watchlist.WatchlistEntity;
import com.homework.bootcamp.service.member.Member;
import com.homework.bootcamp.service.movie.Movie;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

        if (saved.getMovies() != null) convert.setMovies(
        saved.getMovies().stream().map(e -> Movie.convertFrom(e.getMovieEntity())).collect(Collectors.toList()));
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
