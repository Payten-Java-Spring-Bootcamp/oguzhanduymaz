package com.homework.bootcamp.controller.member;

import com.homework.bootcamp.BaseIntegrationTest;
import com.homework.bootcamp.adapter.rest.member.MemberCreateResponse;
import com.homework.bootcamp.adapter.rest.member.MemberRequest;
import com.homework.bootcamp.adapter.rest.member.MemberResponse;
import com.homework.bootcamp.adapter.jpa.member.MemberEntity;
import com.homework.bootcamp.adapter.jpa.member.MemberJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberControllerTest extends BaseIntegrationTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_create_member() {
        //given
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("Test member");
        //when
        ResponseEntity<MemberCreateResponse> response = testRestTemplate.postForEntity("/members", memberRequest, MemberCreateResponse.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        //validate
        Optional<MemberEntity> member = memberJpaRepository.findById(response.getBody().getId());
        assertThat(member).isPresent();
        assertThat(member.get().getId()).isEqualTo(response.getBody().getId());

    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_get_member() {
        //given
        MemberEntity entity = new MemberEntity();
        entity.setName("Test member");

        MemberEntity save = memberJpaRepository.save(entity);
        //when
        ResponseEntity<MemberResponse> response = testRestTemplate.getForEntity("/members/" + save.getId(), MemberResponse.class);
        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Test member");
    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void should_get_all_members() {
        //given
        MemberEntity entity = new MemberEntity();
        entity.setName("Test member");
        MemberEntity entity2 = new MemberEntity();
        entity2.setName("Test member2");

        memberJpaRepository.save(entity);
        memberJpaRepository.save(entity2);
        //when
        ResponseEntity<MemberResponse[]> response = testRestTemplate.getForEntity("/members", MemberResponse[].class);
        List<MemberResponse> listOfResponse = List.of(response.getBody());
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listOfResponse)
                .hasSize(2)
                .extracting("name")
                .contains("Test member", "Test member2");
    }

    @Test
    void deleteMember() {
        //TODO
    }
}