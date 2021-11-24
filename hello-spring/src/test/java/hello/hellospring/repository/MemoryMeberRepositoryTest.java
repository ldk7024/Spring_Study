package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMeberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 메소드가 끝날때마다 동작을 하는 콜백 메소드
    public void afterEach() {
        repository.clearstore();
    }

    @Test // save 테스트
    public void save() {
        Member member = new Member();
        member.setName("spring"); // member를 저장하고

        repository.save(member); // repository에 넣는다.

        Member result = repository.findById(member.getId()).get(); // Optional에서 get으로 값을 꺼낸다.
        assertThat(member).isEqualTo(result);  // member와 result가 같은지 확인(Go에서 사용한거와 같음)
    }

    @Test // findByName 테스트
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test // findAll 테스트
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    // tdd: 먼저 테스트 케이스를 만들고 그에 맞춰서 개발을 진행하는 방식 Test Driven Development의 약자로 '테스트 주도 개발'
}
