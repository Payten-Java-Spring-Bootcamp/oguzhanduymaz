package com.homework.bootcamp.repository.movie;

import com.homework.bootcamp.controller.movie.Genre;
import com.homework.bootcamp.repository.actor.ActorEntity;
import com.homework.bootcamp.repository.matching.MatchingEntity;
import com.homework.bootcamp.repository.rate.RateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "movieEntity")
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer releaseYear;

    @Column(nullable = false)
    private String director;

    @OneToMany(mappedBy = "movie")
    private List<RateEntity> rates;

    @OneToMany(mappedBy = "movieEntity")
    private List<MatchingEntity> matchings;
}
