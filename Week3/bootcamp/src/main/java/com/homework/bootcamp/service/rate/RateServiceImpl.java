package com.homework.bootcamp.service.rate;

import com.homework.bootcamp.repository.movie.MovieDao;
import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.rate.RateDao;
import com.homework.bootcamp.repository.rate.RateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService{
    private final RateDao rateDao;
    private final MovieDao movieDao;

    @Override
    public void rateToMovie(Rate rate) {
        MovieEntity movie = movieDao.retrieve(rate.getMovieId());
        RateEntity entity = rate.convertToRateEntity(movie);
        rateDao.rateToMovie(entity);
    }

    @Override
    public List<Rate> retrieveByMovieId(Long movieId) {
        List<RateEntity> rateEntities = rateDao.retrieveByMovieId(movieId);
        return rateEntities.stream().map(Rate::convertFromRateEntity).collect(Collectors.toList());
    }
}
