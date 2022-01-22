package com.homework.bootcamp.controller.movie;

import com.homework.bootcamp.controller.actor.ActorRequest;
import com.homework.bootcamp.service.actor.Actor;
import com.homework.bootcamp.service.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRequest {

    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotNull
    private Integer releaseYear;

    @NotNull
    private String director;


    private List<ActorRequest> cast;
    private List<Long> actorIds;


    public Movie convertToMovie() {
        Movie build = Movie.builder()
                .name(name)
                .genre(genre)
                .director(director)
                .releaseYear(releaseYear)
                .build();
        if (cast != null) build.setCast(cast.stream()
                .map(ActorRequest::convertToActor)
                .collect(Collectors.toList()));
        return build;
    }

    public List<Actor> convertToActors() {
        if (CollectionUtils.isEmpty(cast))
            return new ArrayList<>();

        return cast.stream()
                .map(ActorRequest::convertToActor)
                .collect(Collectors.toList());
    }
}
