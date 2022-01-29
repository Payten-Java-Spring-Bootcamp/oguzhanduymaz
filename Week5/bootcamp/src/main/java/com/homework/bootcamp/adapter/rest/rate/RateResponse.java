package com.homework.bootcamp.adapter.rest.rate;

import com.homework.bootcamp.domain.rate.Rate;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RateResponse {
    private Long memberId;
    private Long movieId;
    private Integer point;

    private static RateResponse convertFromRate(Rate rate){
        return RateResponse.builder()
                .memberId(rate.getMemberId())
                .movieId(rate.getMovieId())
                .point(rate.getPoint())
                .build();
    }

    public static List<RateResponse> convertFromRate(List<Rate> rates) {
        return rates.stream().map(RateResponse::convertFromRate).collect(Collectors.toList());
    }
}
