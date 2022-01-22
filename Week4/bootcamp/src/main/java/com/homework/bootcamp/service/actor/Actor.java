package com.homework.bootcamp.service.actor;

import com.homework.bootcamp.repository.actor.ActorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Builder
@Getter
public class Actor {
    private Long id;
    private String name;
    private LocalDateTime birthDate;

    public ActorEntity convertToActorEntity() {
        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName(name);
        actorEntity.setBirthDate(birthDate);
        return actorEntity;
    }

    public static Actor convertFrom(ActorEntity entity){
        return Actor.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
