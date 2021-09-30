package com.study.hellospring.service;

import com.study.hellospring.domain.Member;
import com.study.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    //테스트는 순서가 랜덤이기때문에 저장이나 변수가 겹쳐서 에러가 날수 있으니 각 개체 대이터 클리어 해준다
    //자바 8 지금버전에서는 @Test에서 각개체에 연관성이 없게 만들어주기 때문에 괜찮다
    // service에서 레파지토리Dto 를 만든 덕분에  레파지토리가 통일됬다.
    // 이것을 Di 의존성 주입이라고 하고 이렇게 외부 (테스트 )에서 만든 레파지토리를
    //MemberService에서 사용할 수 있게 한다.
    // test에서 새로운 레파지토리를 생성한것이 -> memberService에 있는 레파지토리에 주입됨
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService((memberRepository));
    }


    @Test
    void join() {
        //given 뭘가지고
        Member member = new Member();
        member.setName("hello");


        //when 실행했을때
        Long saveId = memberService.join(member);


        //than 이 결과가 나와야해
        //Assertions 는 alt+enter로 스태틱 임포트로 보내준다
        //setName후 join을 이용해 만든 member와  findMember로 찾은 멤버가 같아야 한다
        //그래야 가입이 된거임
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void validateDuplicate() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        
        //member2를 join 할때 IllegalStateException이 터져야한다
        memberService.join(member1);
        //이렇게 쓰는게 가장 간편하지만
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        --------------------------------------------------------------------------
        // ctrl alt v 를 눌러서 e값에 담아준다음에 isEqualTo를 써줄수도 있다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // -----------------------------------------------------------------
        // try catch를 이용해 잡을 수 있지만 비효율적
//        memberService.join(member1);
//        try{
//            memberService.join(member2);
//              fail("예외가 발생해야 합니다");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }


    @Test
    void findMembers() {


    }

    @Test
    void findOne() {
    }
}