package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //  회원 정보 저장
    Optional<Member> findById(Long id); //  회원 아이디 조회, null 값 방지용으로 optional 사용
    Optional<Member> findByName(String name);   //  회원 이름 조회
    List<Member> findAll(); //  회원 정보 전부 조회



}
