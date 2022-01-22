package com.homework.bootcamp.repository.watchlist;

import com.homework.bootcamp.repository.member.MemberJpaRepository;
import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.movie.MovieJpaRepository;
import com.homework.bootcamp.repository.movieWL.MovieWLEntity;
import com.homework.bootcamp.repository.movieWL.MovieWLJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistDaoImpl implements WatchlistDao{

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
        }else throw new RuntimeException();
    }

    @Override
    public List<WatchlistEntity> retrieveByMemberId(Long memberId) {
        return watchlistJpaRepository.findAllByMemberId(memberId);
    }
}
