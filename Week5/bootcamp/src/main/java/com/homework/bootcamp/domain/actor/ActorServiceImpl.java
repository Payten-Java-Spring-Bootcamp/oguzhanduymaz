package com.homework.bootcamp.domain.actor;

import com.homework.bootcamp.domain.port.ActorPersistencePort;
import com.homework.bootcamp.domain.port.MoviePersistencePort;
import com.homework.bootcamp.domain.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorPersistencePort actorPersistencePort;
    private final MoviePersistencePort moviePersistencePort;

    @Override
    public List<Movie> retrieveMovies(Long actorId) {
        return moviePersistencePort.retrieveByActorId(actorId);
    }

    @Override
    public Long create(Actor actor) {
        return actorPersistencePort.save(actor);
    }
}
