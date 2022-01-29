package com.homework.bootcamp.adapter.jpa.movie;

import com.homework.bootcamp.adapter.jpa.rate.RateEntity;
import com.homework.bootcamp.adapter.rest.movie.Genre;
import com.homework.bootcamp.adapter.jpa.matching.MatchingEntity;
import com.homework.bootcamp.domain.movie.Movie;
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

    public static MovieEntity from(Movie movie) {
        MovieEntity entity = new MovieEntity();
        entity.setId(movie.getId());
        entity.setDirector(movie.getDirector());
        entity.setGenre(movie.getGenre());
        entity.setName(movie.getName());
        entity.setReleaseYear(movie.getReleaseYear());
        return entity;
    }

    public Movie toModel() {
        return Movie.builder()
                .id(id)
                .name(name)
                .genre(genre)
                .releaseYear(releaseYear)
                .director(director)
                .build();
    }
}
