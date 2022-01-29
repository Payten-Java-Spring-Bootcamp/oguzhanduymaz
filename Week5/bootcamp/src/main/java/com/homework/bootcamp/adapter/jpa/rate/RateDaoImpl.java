package com.homework.bootcamp.adapter.jpa.rate;

import com.homework.bootcamp.domain.port.RatePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateDaoImpl implements RatePersistencePort {

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
