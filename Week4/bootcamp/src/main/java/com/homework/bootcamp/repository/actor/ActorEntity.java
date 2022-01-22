package com.homework.bootcamp.repository.actor;

import com.homework.bootcamp.repository.matching.MatchingEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "actorEntity")
@Table(name = "actor")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "actorEntity")
    private List<MatchingEntity> matchings;

}