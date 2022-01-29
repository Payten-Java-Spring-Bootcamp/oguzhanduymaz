package com.homework.bootcamp.domain.watchlist;

import com.homework.bootcamp.adapter.jpa.member.MemberDao;
import com.homework.bootcamp.adapter.jpa.member.MemberEntity;
import com.homework.bootcamp.domain.port.MoviePersistencePort;
import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.domain.port.MovieWLPersistencePort;
import com.homework.bootcamp.adapter.jpa.movieWL.MovieWLEntity;
import com.homework.bootcamp.domain.port.WatchlistPersistencePort;
import com.homework.bootcamp.adapter.jpa.watchlist.WatchlistEntity;
import com.homework.bootcamp.domain.member.Member;
import com.homework.bootcamp.domain.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService{
    private final WatchlistPersistencePort watchlistPersistencePort;
    private final MovieWLPersistencePort movieWLPersistencePort;
    private final MoviePersistencePort moviePersistencePort;
    private final MemberDao memberDao;

    @Override
    public Long create(Watchlist watchlist,Long memberId, List<Long> moviesIds) {
        MemberEntity retrieve = memberDao.retrieve(memberId);
        List<Movie> movies = new ArrayList<>();
        if (moviesIds != null && !moviesIds.isEmpty()) movies = moviePersistencePort.findAllByIdIn(moviesIds);
        watchlist.setOwner(Member.convertFrom(retrieve));
        watchlist.setMovies(movies);
        WatchlistEntity saved = watchlistPersistencePort.save(watchlist.convertToWatchlistEntity(),memberId,moviesIds);
        return saved.getId();
    }

    @Override
    public void addMovieToWatchlist(Long watchlistId, List<Long> movieIds) {
        try {
            WatchlistEntity entity = watchlistPersistencePort.retrieveById(watchlistId);
            List<Movie> movies = moviePersistencePort.findAllByIdIn(movieIds);
            List<MovieWLEntity> wlEntities = movies.stream().map(movieEntity -> {
                MovieWLEntity movieWLEntity = new MovieWLEntity();
                movieWLEntity.setWatchlistEntity(entity);
                movieWLEntity.setMovieEntity(MovieEntity.from(movieEntity));
                return movieWLEntity;
            }).collect(Collectors.toList());
            movieWLPersistencePort.saveAll(wlEntities);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Watchlist> retrieveAll(Long memberId) {
        List<WatchlistEntity> watchlistEntities = watchlistPersistencePort.retrieveByMemberId(memberId);
        return watchlistEntities.stream().map(Watchlist::convertFrom).collect(Collectors.toList());
    }
}
