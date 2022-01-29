package com.homework.bootcamp.adapter.jpa.movieWL;


import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.adapter.jpa.watchlist.WatchlistEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "movieWLEntity")
@Table(name = "movieWL")
public class MovieWLEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private WatchlistEntity watchlistEntity;

    @ManyToOne
    private MovieEntity movieEntity;

}
