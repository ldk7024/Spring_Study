package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }


//    @Bean
//    public MemberRepository memberRepository(){
//       //  return new MemoryMemberRepository();  -> 자체 저장소
//       //  return new JdbcMemberRepository(dataSource); -> jdbc
//       // return new JdbcTemplateMemberRepository(dataSource); -> jdbc template
//       // return new JpaMemberRepository(em);
//    }

    // 개방-폐쇄 원칙 (ocp open closed principle) : 확장에는 열려있고, 수정 변경에는 닫혀있다.
    // 다형성이라는 개념을 잘 활용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
    // 스프링 DI를 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.

}
