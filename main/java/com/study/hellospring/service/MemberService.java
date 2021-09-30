package com.study.hellospring.service;


import com.study.hellospring.domain.Member;
import com.study.hellospring.repository.MemberRepository;
import com.study.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service 컴포넌트 스캔대신 config로 직접등록해서 사용중
// @Service 를 붙여서 스프링 컨테이너에 등록
//@Service
//JPA를쓸때 데이터를 저장,변경 할때 @Transactional을 써야한다
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // private final 에서 new를 만들지 않고 멤버 서비스라는 Dto를 만들어서
    // 모두 똑같은 레파지토리를 쓰게 한다. (테스트포함)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입

    public Long join(Member member) {


        Long start = System.currentTimeMillis();
        //같은 이름이 있는 중복 회원 X
        //기존에는 ifNotNull 과같은걸 쓰지만
        // 이제는 Optional 태그를 쓰고 ifPresent 로 편리하게 해줄 수 있다.
        //굳이 result로 받지않고 바로 붙일 수 있다.
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //ctrl+alt+shift+T 로 메서드 추출 후 사용권장

        //시간을 잴때
        //하나하나 try finally 문을 써주면 유지보수가 힘들고 핵심사항을 파악하기 힘들다
        //그래서 aop를 사용한다
//        try {
//            validateDuplicateMember(member); //중복회원검증
//            memberRepository.save(member);
//            return member.getId();
//        } finally {
//            Long finish = System.currentTimeMillis();
//            Long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }

        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId();
    }


    //중복확인 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회

    public List<Member> findMembers() {
        Long start = System.currentTimeMillis();

        return memberRepository.findAll();

    }


    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
