package com.homework.bootcamp;

import com.homework.bootcamp.Model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @PostMapping("members/{memberId}/rateMovie")
    @ResponseStatus(HttpStatus.OK)
    public Movie rateMovie(@PathVariable Long memberId, @RequestParam Long movieId, @RequestParam Long point){
        return Movie.builder()
                .id(movieId)
                .point(point)
                .name("Spiderman No way home")
                .director("Jon Watts")
                .genre(Genre.FANTASY)
                .releaseYear(2021)
                .cast(List.of(Actor.builder().name("Tom Holland").build())).build();
    }

    @PostMapping("members/{memberId}/createWatchlist")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Watchlist> createWatchlist(@RequestBody Watchlist watchlist, @PathVariable Long memberId){
        Member member = Member.builder().id(memberId).watchlist(List.of(watchlist)).build();
        return member.getWatchlist();
    }

    @GetMapping("members/{memberId}/watchlists")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Watchlist> getWatchlist( @PathVariable Long memberId){
        Watchlist wl = Watchlist.builder().watchlistName("test").movies(List.of(
                Movie.builder().
                        name("Spiderman No way home").
                        director("Jon Watts").
                        genre(Genre.FANTASY).
                        releaseYear(2021).
                        cast(List.of(Actor.builder().name("Tom Holland").build())).build()
        )).build();
        Member member = Member.builder().watchlist(List.of(wl)).build();
        return member.getWatchlist();
    }

    @PostMapping("members/{memberId}/addMovie/{watchlistId}")
    @ResponseStatus(HttpStatus.OK)
    public Watchlist addMovieWatchlist(@PathVariable Long memberId, @PathVariable Long watchlistId, @RequestParam Long movieId){
        Member member = Member.builder().id(memberId).build();
        Watchlist wl = Watchlist.builder().id(watchlistId).movies(List.of(Movie.builder().id(movieId).build())).build();
        member.setWatchlist(List.of(wl));
        return wl;
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember(@RequestBody Member member){
        return Member.builder()
                .name("Test Test")
                .id(1L)
                .build();
    }

    @GetMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public Member getMember(@PathVariable Long memberId){
        return Member.builder()
                .name("Test Test")
                .id(1L)
                .build();
    }

    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getAllMembers(){
        Member member = Member.builder()
                .name("Test Test")
                .id(1L)
                .build();
        return List.of(member);
    }

    @DeleteMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long memberId){
    }
}
