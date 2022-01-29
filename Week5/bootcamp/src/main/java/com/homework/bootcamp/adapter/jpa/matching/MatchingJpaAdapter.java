package com.homework.bootcamp.adapter.jpa.matching;

import com.homework.bootcamp.adapter.jpa.actor.ActorEntity;
import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.domain.actor.Actor;
import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.port.MatchingPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingJpaAdapter implements MatchingPersistencePort {
    private final MatchingJpaRepository matchingJpaRepository;

    @Override
    public void create(Movie movie, List<Actor> actors) {
        List<MatchingEntity> matchingEntities = actors.stream()
                .map(actorEntity -> {
                    MatchingEntity matchingEntity = new MatchingEntity();
                    matchingEntity.setMovieEntity(MovieEntity.from(movie));
                    matchingEntity.setActorEntity(ActorEntity.from(actorEntity));
                    return matchingEntity;
                }).collect(Collectors.toList());

        matchingJpaRepository.saveAll(matchingEntities);
    }
}
