package com.homework.bootcamp.controller.actor;

import com.homework.bootcamp.service.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ActorCreateRequest {
    @NotBlank
    private String name;

    @NotNull
    private LocalDateTime birthDate;

    public Actor convertToActor() {
        return Actor.builder()
                .name(name)
                .birthDate(birthDate)
                .build();
    }
}
