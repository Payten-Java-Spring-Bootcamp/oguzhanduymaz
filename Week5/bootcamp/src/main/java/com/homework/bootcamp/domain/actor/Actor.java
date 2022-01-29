package com.homework.bootcamp.domain.actor;

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


}
