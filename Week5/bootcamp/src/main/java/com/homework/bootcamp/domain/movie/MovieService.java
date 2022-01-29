package com.homework.bootcamp.domain.movie;

import com.homework.bootcamp.domain.actor.Actor;
import com.homework.bootcamp.domain.exception.ExceptionType;
import com.homework.bootcamp.domain.exception.PatikaDataNotFoundException;
import com.homework.bootcamp.domain.port.ActorPersistencePort;
import com.homework.bootcamp.domain.port.MatchingPersistencePort;
import com.homework.bootcamp.domain.port.MovieCachePort;
import com.homework.bootcamp.domain.port.MoviePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MoviePersistencePort moviePersistencePort;
    private final ActorPersistencePort actorPersistencePort;
    private final MatchingPersistencePort matchingPersistencePort;
    private final MovieCachePort movieCachePort;


    public Long create(Movie movie, List<Actor> actors, List<Long> actorIds) {
        List<Actor> existingActors = retrieveExistingActors(actorIds);
        List<Actor> createdActors = createActors(actors);
        Movie createdMovie = moviePersistencePort.save(movie);

        ArrayList<Actor> newActors = new ArrayList<>();
        newActors.addAll(existingActors);
        newActors.addAll(createdActors);



        matchingPersistencePort.create(createdMovie, newActors);
        return createdMovie.getId();
    }

    private List<Actor> createActors(List<Actor> actors) {
        if (!CollectionUtils.isEmpty(actors)){
            return actorPersistencePort.create(actors);
        }
        return new ArrayList<>();
    }

    private List<Actor> retrieveExistingActors(List<Long> actorIds) {
        if (!CollectionUtils.isEmpty(actorIds)){
            List<Actor> retrieved = actorPersistencePort.retrieve(actorIds);
            if (retrieved.size() < actorIds.size()){
                throw new PatikaDataNotFoundException(ExceptionType.COLLECTION_SIZE_EXCEPTION);
            }
            return retrieved;
        }
        return new ArrayList<>();
    }

    public Movie retrieve(Long id) {
        Movie movie = movieCachePort.retrieveMovie(id);
        log.info("Movie is retrieving: {}",id);
        if (movie == null){
            log.info("Movie is updating: {}",id);
            movie = moviePersistencePort.retrieve(id);
            movieCachePort.createMovie(movie);
        }
        return movie;
    }


    public List<Movie> retrieveAll() {
        return moviePersistencePort.retrieveAll();
    }


    public void delete(Long id) {
        moviePersistencePort.delete(id);
    }
}
