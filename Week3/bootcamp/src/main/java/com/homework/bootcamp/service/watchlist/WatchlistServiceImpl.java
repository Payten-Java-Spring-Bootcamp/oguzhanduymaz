package com.homework.bootcamp.service.watchlist;

import com.homework.bootcamp.repository.member.MemberDao;
import com.homework.bootcamp.repository.member.MemberEntity;
import com.homework.bootcamp.repository.movie.MovieDao;
import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.movieWL.MovieWLDao;
import com.homework.bootcamp.repository.movieWL.MovieWLEntity;
import com.homework.bootcamp.repository.watchlist.WatchlistDao;
import com.homework.bootcamp.repository.watchlist.WatchlistEntity;
import com.homework.bootcamp.service.member.Member;
import com.homework.bootcamp.service.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService{
    private final WatchlistDao watchlistDao;
    private final MovieWLDao movieWLDao;
    private final MovieDao movieDao;
    private final MemberDao memberDao;

    @Override
    public Watchlist create(Watchlist watchlist,Long memberId, List<Long> moviesIds) {
        MemberEntity retrieve = memberDao.retrieve(memberId);
        List<MovieEntity> movieEntities = movieDao.findAllByIdIn(moviesIds);
        watchlist.setOwner(Member.convertFrom(retrieve));
        watchlist.setMovies(movieEntities.stream().map(Movie::convertFrom).collect(Collectors.toList()));
        WatchlistEntity saved = watchlistDao.save(watchlist.convertToWatchlistEntity(),memberId,moviesIds);
        return Watchlist.convertFrom(saved);
    }

    @Override
    public void addMovieToWatchlist(Long watchlistId, List<Long> movieIds) {
        try {
            WatchlistEntity entity = watchlistDao.retrieveById(watchlistId);
            List<MovieEntity> movieEntities = movieDao.findAllByIdIn(movieIds);
            List<MovieWLEntity> wlEntities = movieEntities.stream().map(movieEntity -> {
                MovieWLEntity movieWLEntity = new MovieWLEntity();
                movieWLEntity.setWatchlistEntity(entity);
                movieWLEntity.setMovieEntity(movieEntity);
                return movieWLEntity;
            }).collect(Collectors.toList());
            movieWLDao.saveAll(wlEntities);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }
}
