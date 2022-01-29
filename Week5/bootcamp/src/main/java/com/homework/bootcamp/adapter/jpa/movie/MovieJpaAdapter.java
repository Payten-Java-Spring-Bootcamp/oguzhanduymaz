package com.homework.bootcamp.adapter.jpa.movie;


import com.homework.bootcamp.adapter.jpa.actor.ActorEntity;
import com.homework.bootcamp.adapter.jpa.actor.ActorJpaRepository;
import com.homework.bootcamp.adapter.jpa.matching.MatchingEntity;
import com.homework.bootcamp.domain.exception.ExceptionType;
import com.homework.bootcamp.domain.exception.PatikaDataNotFoundException;
import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.port.MoviePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieJpaAdapter implements MoviePersistencePort {
    private final MovieJpaRepository movieJpaRepository;
    private final ActorJpaRepository actorJpaRepository;

    @Override
    public Movie save(Movie entity) {
       return movieJpaRepository.save(MovieEntity.from(entity)).toModel();
    }

    @Override
    public Movie retrieve(Long id) {
        Optional<MovieEntity> movieEntity = movieJpaRepository.findById(id);
        if (movieEntity.isPresent()){
            return movieEntity.get().toModel();
        }else{
            throw new PatikaDataNotFoundException(ExceptionType.MOVIE_DATA_NOT_FOUND, "Movie id:" + id);
        }
    }

    @Override
    public List<Movie> retrieveAll() {
        return movieJpaRepository.findAll()
                .stream()
                .map(MovieEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long Id) {
        movieJpaRepository.deleteById(Id);
    }

    @Override
    public List<Movie> retrieveByActorId(Long actorId) {
        Optional<ActorEntity> actorEntityOptional = actorJpaRepository.findById(actorId);
        if (actorEntityOptional.isPresent()){
            return actorEntityOptional.get()
                    .getMatchings()
                    .stream()
                    .map(MatchingEntity::getMovieEntity)
                    .collect(Collectors.toList())
                    .stream()
                    .map(MovieEntity::toModel)
                    .collect(Collectors.toList());

        }else
            throw new PatikaDataNotFoundException(ExceptionType.ACTOR_DATA_NOT_FOUND);
    }

    @Override
    public List<Movie> findAllByIdIn(List<Long> movieIds) {
        return movieJpaRepository.findAllByIdIn(movieIds)
                .stream()
                .map(MovieEntity::toModel)
                .collect(Collectors.toList());
    }
}
