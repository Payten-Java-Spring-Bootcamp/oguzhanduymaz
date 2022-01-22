package com.homework.bootcamp.controller.watchlist;

import com.homework.bootcamp.service.watchlist.Watchlist;
import com.homework.bootcamp.service.watchlist.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/watchlist")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchlistCreateResponse createWatchlist(@RequestBody @Valid WatchlistCreateRequest request){
        Watchlist watchlist = request.convertToWatchlist();
        Long id = watchlistService.create(watchlist, request.getMemberId(), request.getMoviesIds());
        return WatchlistCreateResponse.convertFrom(id);
    }

    @PostMapping("/watchlist/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovieToWatchlist(@RequestBody @Valid WatchlistAddRequest request){
        watchlistService.addMovieToWatchlist(request.getWatchlistId(),request.getMoviesIds());
    }

    @GetMapping("/watchlist/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public List<WatchlistResponse> getWatchlist(@PathVariable Long memberId){
        List<Watchlist> watchlists = watchlistService.retrieveAll(memberId);
        return watchlists.stream().map(WatchlistResponse::convertFrom).collect(Collectors.toList());
    }


}
