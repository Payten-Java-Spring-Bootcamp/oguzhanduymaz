package com.homework.bootcamp.domain.rate;

import com.homework.bootcamp.adapter.jpa.member.MemberDao;
import com.homework.bootcamp.adapter.jpa.member.MemberEntity;
import com.homework.bootcamp.domain.movie.Movie;
import com.homework.bootcamp.domain.port.MoviePersistencePort;
import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.domain.port.RatePersistencePort;
import com.homework.bootcamp.adapter.jpa.rate.RateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService{
    private final RatePersistencePort ratePersistencePort;
    private final MoviePersistencePort moviePersistencePort;
    private final MemberDao memberDao;

    @Override
    public void rateToMovie(Rate rate) {
        Movie movie = moviePersistencePort.retrieve(rate.getMovieId());
        MemberEntity member = memberDao.retrieve(rate.getMemberId());
        RateEntity entity = rate.convertToRateEntity(MovieEntity.from(movie));
        entity.setMember(member);
        ratePersistencePort.rateToMovie(entity);
    }

    @Override
    public List<Rate> retrieveByMovieId(Long movieId) {
        List<RateEntity> rateEntities = ratePersistencePort.retrieveByMovieId(movieId);
        return rateEntities.stream().map(Rate::convertFromRateEntity).collect(Collectors.toList());
    }
}
