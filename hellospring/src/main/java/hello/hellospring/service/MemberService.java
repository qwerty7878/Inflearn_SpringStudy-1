package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//  데이터를 저장하고 변경할 때 항상 필요
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join(Member member) {
//
//        long start = System.currentTimeMillis();
//
//        try {

//        같은 이름 중복 방지
        validateDuplicateMember(member);
//        command option m
        memberRepository.save(member);
        return member.getId();

//        } finally {   aop 를 사용하는 이유 단순반복되는 걸 통합시킴
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }

    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
//      optional을 바로 반환하기 보단 optional을 반환하는 경우에는 생략할 수 있음
                .ifPresent(m -> { //  널값이 아닌 어떠한 값이 있다면(ifpresent) optional 이 널값 방지하기 때문
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {

//        전체 회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
