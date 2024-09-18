package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //  필드 주입방식(비추) / 바꿀 수 있는 방식이 없기때문
//    @Autowired private MemberService memberService;
//
    private MemberService memberService;

//    @Autowired  setter 주입방 / 무조건 public으로 선언해야함, 중간에 내용 변경시 문제가 발생할 수 있음
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 의존성 주입 di
    // @repo, @service 이런걸 알아서 찾아서 넣어줌
    // 컴포넌트 스캔방식

    @Autowired
//    생성자 주입방식 (최근 사용하는 방식)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(memberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
