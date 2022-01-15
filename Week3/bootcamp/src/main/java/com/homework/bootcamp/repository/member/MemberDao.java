package com.homework.bootcamp.repository.member;

import java.util.List;

public interface MemberDao {

    Long save(MemberEntity entity);

    MemberEntity retrieve(Long id);

    List<MemberEntity> retrieveAll();

    void delete(Long Id);

}
