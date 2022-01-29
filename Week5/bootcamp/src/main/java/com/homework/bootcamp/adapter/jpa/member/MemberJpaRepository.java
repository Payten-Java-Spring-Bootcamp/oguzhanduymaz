package com.homework.bootcamp.adapter.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity,Long> {
}
