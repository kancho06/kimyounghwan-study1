package com.study.hellospring.controller;


import com.study.hellospring.domain.Member;
import com.study.hellospring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


//@Controller 를 붙임으로써 스프링컨테이너 박스안에 등록
//컴포넌트 스캔방식임 @Component 안에 @Controller 로 등록이됨
//주입의 3가지 방법

@Controller
public class MemberController {
    //1. 필드주입 (별로 안좋다 )
    //바꿀수 있는 방법이 없다. final 빠짐
//    @Autowired private  MemberService memberService;

    //2.생성자 주입 (요즘 권장하는 주입)
    //생성자에 Autowired 를 붙이면 스프링Bean 에 등록되어있는
    // 서비스를 찾아서 연동시켜줌 Di(의존성주입)
    //(재료)안의 것을 찾아서 넣어준다고 생각하면됨
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //3.세터주입
    //단점: 누군가가 멤버서비스를 호출 했을때
    //퍼블릭으로 열려있어야 한다.(final 빠져서)
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member =" + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }


}
