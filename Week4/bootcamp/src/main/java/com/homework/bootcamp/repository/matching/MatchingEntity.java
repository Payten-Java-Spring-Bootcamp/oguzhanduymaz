package com.homework.bootcamp.repository.matching;

import com.homework.bootcamp.repository.actor.ActorEntity;
import com.homework.bootcamp.repository.movie.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "matching")
@Table(name = "matching")
public class MatchingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MovieEntity movieEntity;

    @ManyToOne
    private ActorEntity actorEntity;
}