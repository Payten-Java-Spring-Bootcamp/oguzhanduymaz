package com.homework.bootcamp.adapter.jpa.watchlist;

import com.homework.bootcamp.adapter.jpa.member.MemberJpaRepository;
import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.adapter.jpa.movie.MovieJpaRepository;
import com.homework.bootcamp.adapter.jpa.movieWL.MovieWLEntity;
import com.homework.bootcamp.adapter.jpa.movieWL.MovieWLJpaRepository;
import com.homework.bootcamp.domain.exception.ExceptionType;
import com.homework.bootcamp.domain.exception.PatikaDataNotFoundException;
import com.homework.bootcamp.domain.port.WatchlistPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistDaoImpl implements WatchlistPersistencePort {

    private final WatchlistJpaRepository watchlistJpaRepository;
    private final MovieWLJpaRepository wlJpaRepository;
    private final MovieJpaRepository movieJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    @Override
    public WatchlistEntity save(WatchlistEntity watchlist,Long memberId, List<Long> movieIds) {
        List<MovieEntity> allByIdIn = new ArrayList<>();
        if(movieIds != null) allByIdIn = movieJpaRepository.findAllByIdIn(movieIds);
        watchlist.setMember(memberJpaRepository.getById(memberId));
        WatchlistEntity saved = watchlistJpaRepository.save(watchlist);
        List<MovieWLEntity> collect = allByIdIn.stream()
                .map(a -> {
                    MovieWLEntity wlEntity = new MovieWLEntity();
                    wlEntity.setMovieEntity(a);
                    wlEntity.setWatchlistEntity(saved);
                    return wlEntity;
                })
                .collect(Collectors.toList());
        wlJpaRepository.saveAll(collect);
        return saved;
    }

    @Override
    public WatchlistEntity retrieveById(Long watchlistId) {
        Optional<WatchlistEntity> retrieve = watchlistJpaRepository.findById(watchlistId);
        if (retrieve.isPresent()){
            return retrieve.get();
        }else throw new PatikaDataNotFoundException(ExceptionType.WATCHLIST_DATA_NOT_FOUND);
    }

    @Override
    public List<WatchlistEntity> retrieveByMemberId(Long memberId) {
        return watchlistJpaRepository.findAllByMemberId(memberId);
    }
}
