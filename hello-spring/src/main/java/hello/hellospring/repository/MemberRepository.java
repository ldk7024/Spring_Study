package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save (Member member);
    Optional<Member> findById(Long id); // Optional : 값이 없으면 null로 처리하는 방법
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
