package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;



public class MemoryMemberRepository implements MemberRepository{
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */
    private static Map<Long, Member> store = new HashMap<>();
    private  static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Null이여도 사용 가능하게
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 파라미터로 넘어온 name과 member에 있는 name이 같은 것을 필터링해서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearstore() {   // store에 저장되어있는 데이터를 비워줌
        store.clear();
    }
}
