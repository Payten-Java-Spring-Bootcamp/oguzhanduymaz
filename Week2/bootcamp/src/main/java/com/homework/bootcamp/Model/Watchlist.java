package com.homework.bootcamp.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class Watchlist {
    Long id;
    String watchlistName;
    List<Movie> movies;
}
