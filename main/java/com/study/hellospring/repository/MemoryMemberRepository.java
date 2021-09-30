package com.study.hellospring.repository;

import com.study.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository 컴포넌트 스캔대신 config로 직접등록해서 사용중
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static Long sequence =0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }
    //Null이나올 가능성이 있는곳엔 Optional로 감싼다
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    //네임을 넣고 돌려서 찾는다 없을 경우 Null반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
