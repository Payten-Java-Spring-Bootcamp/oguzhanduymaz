package com.homework.bootcamp.repository.movieWL;

import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.watchlist.WatchlistEntity;

import java.util.List;

public interface MovieWLDao {
    void saveAll(List<MovieWLEntity> wlEntities);
}
