package com.homework.bootcamp.repository.movieWL;


import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.watchlist.WatchlistEntity;
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
