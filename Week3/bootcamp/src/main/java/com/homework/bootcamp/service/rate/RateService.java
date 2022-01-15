package com.homework.bootcamp.service.rate;

import java.util.List;

public interface RateService {
    void rateToMovie(Rate rate);
    List<Rate> retrieveByMovieId(Long movieId);
}
