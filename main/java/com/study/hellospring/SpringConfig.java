package com.study.hellospring;


import com.study.hellospring.aop.TimeTraceAop;
import com.study.hellospring.repository.MemberRepository;
import com.study.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.AssociationOverride;


//@Service 같이 자동등록을 사용하지않고
// 이렇게 @Configuration 으로 수동 등록 할 수 있다.
//@Controller 는 자동등록 해야한다.
//이렇게 @Configuration 을 이용해서 하는 방법의 이점은
//db 를 안정하고하거나 새로 바꿀때 이다.
//@Service 같이 컴포넌트 스캔을 이용할 경우 나중에 db를 새로 넣거나 변경할때
//객체 이름을 DbMemoryMemberRepository로만 변경해주면 된다
@Configuration
public class SpringConfig {

      private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//    private EntityManager em;

//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    //멤버서비스에서 @Commponent를 써도 되지만 여기에서 하는게 실무에서 좋다.
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new JpaMemberRepository(em);
//
//    }
}
