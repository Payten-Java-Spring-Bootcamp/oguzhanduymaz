package com.homework.bootcamp.controller.actor;

import com.homework.bootcamp.repository.matching.MatchingEntity;
import com.homework.bootcamp.service.actor.Actor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
public class ActorResponse {

    @NotNull
    String name;

    @NotNull
    LocalDateTime birthDate;

    public static ActorResponse convertFrom(MatchingEntity actor) {
        return ActorResponse.builder()
                .name(actor.getActorEntity().getName())
                .birthDate(actor.getActorEntity().getBirthDate())
                .build();
    }

    public static ActorResponse convertFromActor(Actor actor) {
        return ActorResponse.builder()
                .name(actor.getName())
                .birthDate(actor.getBirthDate())
                .build();
    }
}
