package com.homework.bootcamp.controller.actor;

import com.homework.bootcamp.service.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActorRequest {

    @NotNull
    String name;

    @NotNull
    LocalDateTime birthDate;


    public Actor convertToActor(){
        return Actor.builder()
                .name(name)
                .birthDate(birthDate)
                .build();
    }

    public static List<Actor> convertToActorList(List<ActorRequest> cast) {
        return cast.stream().map(ActorRequest::convertToActor)
                .collect(Collectors.toList());
    }
}
