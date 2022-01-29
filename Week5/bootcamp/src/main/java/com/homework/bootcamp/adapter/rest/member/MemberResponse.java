package com.homework.bootcamp.adapter.rest.member;

import com.homework.bootcamp.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
