package com.homework.bootcamp.service.rate;

import com.homework.bootcamp.repository.movie.MovieEntity;
import com.homework.bootcamp.repository.rate.RateEntity;
import com.homework.bootcamp.service.member.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Rate {
    private LocalDateTime createdDate;
    private Member member;
    private Long memberId;
    private Long movieId;
    private Integer point;

    public static Rate convertFromRateEntity(RateEntity rate){
        return Rate.builder()
                .createdDate(rate.getCreatedDate())
                .memberId(rate.getId())
                .movieId(rate.getMovie().getId())
                .point(rate.getPoint())
                .build();
    }

    public RateEntity convertToRateEntity(MovieEntity movie) {
        RateEntity rate = new RateEntity();
        rate.setCreatedDate(LocalDateTime.now());
        rate.setMovie(movie);
        rate.setPoint(point);
        return rate;
    }
}
