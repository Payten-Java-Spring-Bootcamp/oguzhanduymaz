package com.homework.bootcamp.domain.port;

import com.homework.bootcamp.adapter.jpa.rate.RateEntity;

import java.util.List;

public interface RatePersistencePort {
    void rateToMovie(RateEntity entity);

    List<RateEntity> retrieveByMovieId(Long movieId);
}
