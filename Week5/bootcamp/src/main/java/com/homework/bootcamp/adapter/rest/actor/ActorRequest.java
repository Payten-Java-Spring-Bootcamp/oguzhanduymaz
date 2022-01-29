package com.homework.bootcamp.adapter.rest.actor;

import com.homework.bootcamp.domain.actor.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
