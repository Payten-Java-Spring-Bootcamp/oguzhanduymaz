package com.homework.bootcamp.repository.rate;

import com.homework.bootcamp.repository.member.MemberEntity;
import com.homework.bootcamp.repository.movie.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "rateEntity")
@Table(name = "rate")
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private Integer point;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MovieEntity movie;

}
