package com.homework.bootcamp.adapter.rest.member;

import com.homework.bootcamp.domain.member.MemberService;
import com.homework.bootcamp.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberCreateResponse create(@RequestBody @Valid MemberRequest request){
        Member member = request.convertToMember();
        Long id = memberService.create(member);
        return MemberCreateResponse.convertToMemberResponse(id);
    }

    @GetMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getMember(@PathVariable Long memberId){
        Member member = memberService.retrieve(memberId);
        return MemberResponse.convertFrom(member);
    }


    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResponse> getAllMembers(){
        List<Member> members = memberService.retrieveAll();
        return members.stream()
                .map(MemberResponse::convertFrom)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/members/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long memberId){
        memberService.delete(memberId);
    }
}
