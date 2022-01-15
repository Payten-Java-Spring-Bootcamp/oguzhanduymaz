package com.homework.bootcamp.controller.member;

import com.homework.bootcamp.service.member.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {
    private String name;

    public static MemberResponse convertFrom(Member member) {
        return MemberResponse.builder()
                .name(member.getName())
                .build();
    }
}
