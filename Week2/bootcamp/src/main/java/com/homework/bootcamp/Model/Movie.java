package com.homework.bootcamp.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Movie {
    Long point;
    Long id;
    String name,director;
    Genre genre;
    Integer releaseYear;
    List<Actor> cast;

}
