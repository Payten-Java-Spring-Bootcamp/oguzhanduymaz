package com.homework.bootcamp.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findAllByIdIn(List<Long> movieIds);
}
