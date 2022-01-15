package com.homework.bootcamp.repository.watchlist;

import com.homework.bootcamp.repository.member.MemberEntity;
import com.homework.bootcamp.repository.movieWL.MovieWLEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "watchlist")
@Table(name = "watchlist")
public class WatchlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private MemberEntity owner;

    @Column(nullable = false)
    private String watchlistName;

    @OneToMany(mappedBy = "watchlistEntity")
    private List<MovieWLEntity> movies;

    @Column(nullable = false)
    private LocalDateTime createdDate;
}
