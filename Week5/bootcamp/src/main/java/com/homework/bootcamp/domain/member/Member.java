package com.homework.bootcamp.domain.member;

import com.homework.bootcamp.adapter.jpa.member.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class Member {
    private Long id;
    private String name;

    public static Member convertFrom(MemberEntity retrieve) {
        return Member.builder()
                .name(retrieve.getName())
                .build();
    }

    public MemberEntity convertToMemberEntity() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(name);
        return memberEntity;
    }
}
