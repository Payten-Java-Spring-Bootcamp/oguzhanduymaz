package com.homework.bootcamp.controller.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateResponse {

    private Long id;

    public static MemberCreateResponse convertToMemberResponse(Long id) {
        return MemberCreateResponse.builder()
                .id(id)
                .build();
    }
}
