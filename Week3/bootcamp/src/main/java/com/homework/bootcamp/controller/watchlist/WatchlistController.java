package com.homework.bootcamp.controller.watchlist;

import com.homework.bootcamp.service.watchlist.Watchlist;
import com.homework.bootcamp.service.watchlist.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/watchlist")
    @ResponseStatus(HttpStatus.OK)
    public void createWatchlist(@RequestBody @Valid WatchlistCreateRequest request){
        Watchlist watchlist = request.convertToWatchlist();
        watchlistService.create(watchlist,request.getMemberId(),request.getMoviesIds());
    }

    @PostMapping("/watchlist/add")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieToWatchlist(@RequestBody @Valid WatchlistAddRequest request){
        watchlistService.addMovieToWatchlist(request.getWatchlistId(),request.getMoviesIds());
    }


}
