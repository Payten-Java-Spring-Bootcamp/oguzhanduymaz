package com.homework.bootcamp.adapter.jpa.movieWL;

import com.homework.bootcamp.domain.port.MovieWLPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieWLDaoImpl implements MovieWLPersistencePort {
    private final MovieWLJpaRepository movieWLJpaRepository;

    @Override
    public void saveAll(List<MovieWLEntity> wlEntities) {
        movieWLJpaRepository.saveAll(wlEntities);
    }
}
