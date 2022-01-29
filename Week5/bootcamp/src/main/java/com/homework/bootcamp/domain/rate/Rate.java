package com.homework.bootcamp.domain.rate;

import com.homework.bootcamp.adapter.jpa.movie.MovieEntity;
import com.homework.bootcamp.adapter.jpa.rate.RateEntity;
import com.homework.bootcamp.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
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
                .memberId(rate.getMember().getId())
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
