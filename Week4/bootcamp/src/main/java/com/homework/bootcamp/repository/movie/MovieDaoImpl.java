package com.homework.bootcamp.repository.movie;


import com.homework.bootcamp.repository.actor.ActorEntity;
import com.homework.bootcamp.repository.actor.ActorJpaRepository;
import com.homework.bootcamp.repository.matching.MatchingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieDaoImpl implements MovieDao{
    private final MovieJpaRepository movieJpaRepository;
    private final ActorJpaRepository actorJpaRepository;

    @Override
    public MovieEntity save(MovieEntity entity) {
       return movieJpaRepository.save(entity);
    }

    @Override
    public MovieEntity retrieve(Long id) {
        Optional<MovieEntity> movieEntity = movieJpaRepository.findById(id);
        if (movieEntity.isPresent()){
            return movieEntity.get();
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public List<MovieEntity> retrieveAll() {
        return movieJpaRepository.findAll();
    }

    @Override
    public void delete(Long Id) {
        movieJpaRepository.deleteById(Id);
    }

    @Override
    public List<MovieEntity> retrieveByActorId(Long actorId) {
        Optional<ActorEntity> actorEntityOptional = actorJpaRepository.findById(actorId);
        if (actorEntityOptional.isPresent()){
            return actorEntityOptional.get()
                    .getMatchings()
                    .stream()
                    .map(MatchingEntity::getMovieEntity)
                    .collect(Collectors.toList());

        }else
            throw new RuntimeException();
    }

    @Override
    public List<MovieEntity> findAllByIdIn(List<Long> movieIds) {
        return movieJpaRepository.findAllByIdIn(movieIds);
    }
}
