package com.homework.bootcamp.repository.rate;

import java.util.List;

public interface RateDao {
    void rateToMovie(RateEntity entity);

    List<RateEntity> retrieveByMovieId(Long movieId);
}
