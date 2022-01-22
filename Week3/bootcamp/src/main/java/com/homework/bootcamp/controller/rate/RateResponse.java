package com.homework.bootcamp.controller.rate;

import com.homework.bootcamp.service.rate.Rate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class RateResponse {
    private LocalDateTime createdDate;
    private Long memberId;
    private Long movieId;
    private Integer point;

    private static RateResponse convertFromRate(Rate rate){
        return RateResponse.builder()
                .createdDate(rate.getCreatedDate())
                .memberId(rate.getMemberId())
                .movieId(rate.getMovieId())
                .point(rate.getPoint())
                .build();
    }

    public static List<RateResponse> convertFromRate(List<Rate> rates) {
        return rates.stream().map(RateResponse::convertFromRate).collect(Collectors.toList());
    }
}
