package com.homework.bootcamp.repository.rate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateDaoImpl implements RateDao{

    private final RateJpaRepository rateJpaRepository;


    @Override
    public void rateToMovie(RateEntity entity) {
        rateJpaRepository.save(entity);

    }

    @Override
    public List<RateEntity> retrieveByMovieId(Long movieId) {
        return rateJpaRepository.findAllByMovieId(movieId);
    }
}
