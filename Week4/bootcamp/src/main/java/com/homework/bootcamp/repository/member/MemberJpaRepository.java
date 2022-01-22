package com.homework.bootcamp.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {
}
