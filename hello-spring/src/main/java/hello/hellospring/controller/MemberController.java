package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있다. 의존관계가 샐행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다. 그리고 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.



@Controller
public class MemberController {
//    private  final MemberService memberService;
     private MemberService memberService;

     @Autowired   // 생성자를 통한 주입
    public  MemberController (MemberService memberService){

         this.memberService = memberService;
     }

//     @Autowired
//    public void setMemberService(MemberService memberService){
//         this.memberService = memberService; // setter 주입 방식 -> 무조건 public으로 노출 되어 있어야함
//    }
//    @Autowired private MemberService memberService; // 필드 주입 방식
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;  // 생성자를 통한 주입
//    }
    @GetMapping("/members/new")
    public String createForm(){
         return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        // System.out.println("member = " + member.getName());


        memberService.join(member);

        return "redirect:/";

    }
    @GetMapping("/members")
    public String list (Model model){
         List<Member> members = memberService.findMembers();
         model.addAttribute("members",members);
         return "members/memberList";
    }

}
