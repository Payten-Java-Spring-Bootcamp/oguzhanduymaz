package com.homework.bootcamp.controller.rate;

import com.homework.bootcamp.service.rate.Rate;
import com.homework.bootcamp.service.rate.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @PostMapping("/rates")
    @ResponseStatus(HttpStatus.OK)
    public void rateMovie(@RequestBody @Valid RateRequest request){
        Rate rate = request.convertToRate();
        rateService.rateToMovie(rate);
    }

    @GetMapping("/rates/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RateResponse> retrieveByMovieId(@PathVariable Long movieId){
        List<Rate> rates = rateService.retrieveByMovieId(movieId);
        return RateResponse.convertFromRate(rates);
    }
}
