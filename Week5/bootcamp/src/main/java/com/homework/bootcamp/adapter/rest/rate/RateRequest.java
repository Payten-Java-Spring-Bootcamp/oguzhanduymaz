package com.homework.bootcamp.adapter.rest.rate;

import com.homework.bootcamp.domain.rate.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RateRequest {
    @NotNull
    private Long memberId;

    @NotNull
    private Long movieId;

    @Min(1)
    @Max(10)
    @NotNull
    private Integer point;

    public Rate convertToRate() {
        return Rate.builder()
                .memberId(memberId)
                .movieId(movieId)
                .point(point)
                .build();
    }
}
