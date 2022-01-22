package com.homework.bootcamp.service.actor;

import com.homework.bootcamp.repository.actor.ActorDao;
import com.homework.bootcamp.repository.movie.MovieDao;
import com.homework.bootcamp.service.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService{

    private final ActorDao actorDao;
    private final MovieDao movieDao;

    @Override
    public List<Movie> retrieveMovies(Long actorId) {
        return movieDao.retrieveByActorId(actorId)
                .stream()
                .map(Movie::convertFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Long create(Actor actor) {
        return actorDao.save(actor.convertToActorEntity());
    }
}
