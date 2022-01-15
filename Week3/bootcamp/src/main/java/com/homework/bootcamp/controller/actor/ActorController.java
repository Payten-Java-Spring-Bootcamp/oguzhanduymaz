package com.homework.bootcamp.controller.actor;

import com.homework.bootcamp.controller.movie.MovieResponse;
import com.homework.bootcamp.service.actor.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @PostMapping("/actors")
    public ActorCreateResponse create(@RequestBody @Valid ActorCreateRequest request) {
        Long createdActorId = actorService.create(request.convertToActor());
        return ActorCreateResponse.builder().id(createdActorId).build();
    }

    @GetMapping("/actors/{actorId}/movies")
    public List<MovieResponse> retrieveMovies(@PathVariable Long actorId) {
        return actorService.retrieveMovies(actorId)
                .stream()
                .map(MovieResponse::convertFrom)
                .collect(Collectors.toList());
    }
}
