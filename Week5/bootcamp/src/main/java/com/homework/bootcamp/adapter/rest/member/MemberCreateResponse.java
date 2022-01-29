package com.homework.bootcamp.adapter.rest.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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
