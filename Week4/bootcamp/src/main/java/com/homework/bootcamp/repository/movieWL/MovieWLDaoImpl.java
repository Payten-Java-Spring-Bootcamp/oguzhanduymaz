package com.homework.bootcamp.repository.movieWL;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieWLDaoImpl implements MovieWLDao {
    private final MovieWLJpaRepository movieWLJpaRepository;

    @Override
    public void saveAll(List<MovieWLEntity> wlEntities) {
        movieWLJpaRepository.saveAll(wlEntities);
    }
}
