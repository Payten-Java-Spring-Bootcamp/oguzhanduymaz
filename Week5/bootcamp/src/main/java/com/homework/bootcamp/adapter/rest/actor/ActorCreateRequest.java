package com.homework.bootcamp.adapter.rest.actor;

import com.homework.bootcamp.domain.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
