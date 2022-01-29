package com.homework.bootcamp.adapter.jpa.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findAllByIdIn(List<Long> movieIds);
}
