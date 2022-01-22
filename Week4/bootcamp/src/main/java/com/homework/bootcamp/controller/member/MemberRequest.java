package com.homework.bootcamp.controller.member;

import com.homework.bootcamp.service.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

    @NotNull
    private String name;


    public Member convertToMember() {
        return Member.builder()
                .name(name)
                .build();
    }
}
