package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 각 메소드가 끝날때마다 동작을 하는 콜백 메소드
    public void afterEach() {
        memberRepository.clearstore();
    }

    @Test
    void 회원가입() {                       //테스트는 한글로 바꿔도 된다. -> 테스트는 직관적으로 알아볼 수 있게 한글로도 적음
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId= memberService.join(member);

        // then
        Member findMember= memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        // when
       memberService.join(member1);
       IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//       } catch (IllegalStateException e){
//           org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//       }

        // then
    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}