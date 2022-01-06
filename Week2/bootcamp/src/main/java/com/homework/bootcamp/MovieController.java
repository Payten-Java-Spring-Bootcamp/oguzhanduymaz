package com.homework.bootcamp;

import com.homework.bootcamp.Model.Actor;
import com.homework.bootcamp.Model.Genre;
import com.homework.bootcamp.Model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie request){
        return Movie.builder()
                .id((long) (Math.random()*100))
                .name("Spiderman No way home")
                .director("Jon Watts")
                .genre(Genre.FANTASY)
                .releaseYear(2021)
                .cast(List.of(Actor.builder().name("Tom Holland").build())).build();
    }

    @GetMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovie(@PathVariable Long movieId){
        return Movie.builder()
                .id(movieId)
                .name("Spiderman No way home")
                .director("Jon Watts")
                .genre(Genre.FANTASY)
                .releaseYear(2021)
                .cast(List.of(Actor.builder().name("Tom Holland").build())).build();
    }

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies(){
        Movie movie = Movie.builder()
                .id(1L)
                .name("Spiderman No way home")
                .director("Jon Watts")
                .genre(Genre.FANTASY)
                .releaseYear(2021)
                .cast(List.of(Actor.builder().name("Tom Holland").build())).build();
        return List.of(movie);
    }

    @DeleteMapping("/movie/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long movieId){
    }


}
