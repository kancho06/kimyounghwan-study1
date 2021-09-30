package com.study.hellospring.repository;

import com.study.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트는 순서가 랜덤이기때문에 저장이나 변수가 겹쳐서 에러가 날수 있으니 각 개체 대이터 클리어 해준다
    //자바 8 지금버전에서는 @Test에서 각개체에 연관성이 없게 만들어주기 때문에 괜찮다
//    @AfterEach
//    public void afterEach() {
//        repository.clearStore();
//    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //첫번째 확인방법
//        System.out.println("result = " + (result == member));
        //두번째 확인방법 jupiter Assertions
//        Assertions.assertEquals(member,result);
        //세번째 확인방법 가장많이씀 asserti.core Assertions
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        //멤버가 2명뿐이라 2넣어줌
        assertThat(result.size()).isEqualTo(2);
    }

//    public void clearStore() {
//        store.clear();
//
//    }
}
