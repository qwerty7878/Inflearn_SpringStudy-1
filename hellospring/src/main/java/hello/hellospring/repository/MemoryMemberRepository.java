package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository{

    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    //  공유자원은 동시성 이유 concurrenthashmap 사용
    private static long sequence = 0L;
    //  키 값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //  고유번호 증가
        store.put(member.getId(), member);  //  멤버 고유번호와 멤버 이름 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //  null값 배제 id값 가져오기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //  반복해서 만약 멤버 안 이름과 같다면
                .findAny(); //  반환해라
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearstore() {
        store.clear();  //  초기화
    }
}
