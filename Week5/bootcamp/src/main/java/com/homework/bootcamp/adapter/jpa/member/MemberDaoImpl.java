package com.homework.bootcamp.adapter.jpa.member;

import com.homework.bootcamp.domain.exception.ExceptionType;
import com.homework.bootcamp.domain.exception.PatikaDataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDaoImpl implements MemberDao{

    private final MemberJpaRepository memberJpaRepository;


    @Override
    public Long save(MemberEntity entity) {
        return memberJpaRepository.save(entity).getId();
    }

    @Override
    public MemberEntity retrieve(Long id) {
        Optional<MemberEntity> member = memberJpaRepository.findById(id);
        if (member.isPresent()){
            return member.get();
        }else{
            throw new PatikaDataNotFoundException(ExceptionType.MEMBER_DATA_NOT_FOUND);
        }
    }

    @Override
    public List<MemberEntity> retrieveAll() {
        return memberJpaRepository.findAll();
    }

    @Override
    public void delete(Long Id) {
        memberJpaRepository.deleteById(Id);
    }
}
