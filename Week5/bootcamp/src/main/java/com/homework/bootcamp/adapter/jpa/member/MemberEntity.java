package com.homework.bootcamp.adapter.jpa.member;

import com.homework.bootcamp.adapter.jpa.rate.RateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "memberEntity")
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "member")
    List<RateEntity> rates;

}
