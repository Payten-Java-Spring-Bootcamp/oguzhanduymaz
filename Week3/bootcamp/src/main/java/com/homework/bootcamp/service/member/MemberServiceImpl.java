package com.homework.bootcamp.service.member;

import com.homework.bootcamp.repository.member.MemberDao;
import com.homework.bootcamp.repository.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberDao memberDao;


    @Override
    public Long create(Member member) {
        return memberDao.save(member.convertToMemberEntity());
    }

    @Override
    public Member retrieve(Long id) {
        MemberEntity retrieve = memberDao.retrieve(id);
        return Member.convertFrom(retrieve);
    }

    @Override
    public List<Member> retrieveAll() {
        List<MemberEntity> memberEntities = memberDao.retrieveAll();
        return memberEntities.stream().map(Member::convertFrom).collect(Collectors.toList());
    }

    @Override
    public void delete(Long Id) {
        memberDao.delete(Id);
    }
}
