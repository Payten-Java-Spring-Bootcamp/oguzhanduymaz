package com.homework.bootcamp.adapter.jpa.actor;

import com.homework.bootcamp.adapter.jpa.matching.MatchingEntity;
import com.homework.bootcamp.domain.actor.Actor;
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

    public static ActorEntity from(Actor actor) {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName(actor.getName());
        actorEntity.setBirthDate(actor.getBirthDate());
        return actorEntity;
    }

    public Actor toModel(){
        return Actor.builder()
                .id(id)
                .name(name)
                .birthDate(birthDate)
                .build();
    }

}