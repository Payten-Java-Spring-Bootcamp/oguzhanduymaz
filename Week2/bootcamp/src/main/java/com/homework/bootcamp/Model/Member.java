package com.homework.bootcamp.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Builder
public class Member {
    Long id;
    String name;
    List<Watchlist> watchlist;
}
