package com.homework.bootcamp.service.movie;

import com.homework.bootcamp.repository.matching.MatchingDao;
import com.homework.bootcamp.repository.matching.MatchingEntity;
import com.homework.bootcamp.service.actor.Actor;
import com.homework.bootcamp.repository.actor.ActorDao;
import com.homework.bootcamp.repository.actor.ActorEntity;
import com.homework.bootcamp.repository.movie.MovieDao;
import com.homework.bootcamp.repository.movie.MovieEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieDao movieDao;
    private final ActorDao actorDao;
    private final MatchingDao matchingDao;


    @Override
    public Long create(Movie movie, List<Actor> actors, List<Long> actorIds) {
        List<ActorEntity> existingActors = retrieveExistingActors(actorIds);
        List<ActorEntity> createdActors = createActors(actors);
        MovieEntity createdMovie = movieDao.save(movie.convertToMovieEntity());

        ArrayList<ActorEntity> actorEntities = new ArrayList<>();
        actorEntities.addAll(existingActors);
        actorEntities.addAll(createdActors);

        List<MatchingEntity> matchingEntities = actorEntities.stream()
                .map(actorEntity -> {
                    MatchingEntity matchingEntity = new MatchingEntity();
                    matchingEntity.setMovieEntity(createdMovie);
                    matchingEntity.setActorEntity(actorEntity);
                    return matchingEntity;
                }).collect(Collectors.toList());

        matchingDao.create(matchingEntities);
        return createdMovie.getId();
    }

    private List<ActorEntity> createActors(List<Actor> actors) {
        if (!CollectionUtils.isEmpty(actors)){
            List<ActorEntity> entities = actors.stream()
                    .map(Actor::convertToActorEntity)
                    .collect(Collectors.toList());
            return actorDao.create(entities);
        }
        return new ArrayList<>();
    }

    private List<ActorEntity> retrieveExistingActors(List<Long> actorIds) {
        if (!CollectionUtils.isEmpty(actorIds)){
            List<ActorEntity> retrieved = actorDao.retrieve(actorIds);
            if (retrieved.size() < actorIds.size()){
                throw new RuntimeException("Db'de bulunamadi");
            }
            return retrieved;
        }
        return new ArrayList<>();
    }

    @Override
    public Movie retrieve(Long id) {
        MovieEntity movie =movieDao.retrieve(id);
        return Movie.convertFrom(movie);
    }

    @Override
    public List<Movie> retrieveAll() {
        List<MovieEntity> movieEntities = movieDao.retrieveAll();
        return movieEntities.stream()
                .map(Movie::convertFrom)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        movieDao.delete(id);
    }
}
