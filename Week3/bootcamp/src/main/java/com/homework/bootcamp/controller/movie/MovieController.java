package com.homework.bootcamp.controller.movie;

import com.homework.bootcamp.service.actor.Actor;
import com.homework.bootcamp.service.movie.Movie;
import com.homework.bootcamp.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieCreateResponse create(@RequestBody @Valid MovieRequest request){
        Movie movie = request.convertToMovie();
        List<Actor> actors = request.convertToActors();

        Long id = movieService.create(movie,actors,request.getActorIds());
        return MovieCreateResponse.convertToMovieResponse(id);
    }

    @GetMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse getMovie(@PathVariable Long movieId){
        Movie movie = movieService.retrieve(movieId);
        return MovieResponse.convertFrom(movie);
    }

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> getAllMovie(){
        List<Movie> movie = movieService.retrieveAll();
        return movie.stream()
                .map(MovieResponse::convertFrom)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/movie/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long movieId){
        movieService.delete(movieId);
    }

}
