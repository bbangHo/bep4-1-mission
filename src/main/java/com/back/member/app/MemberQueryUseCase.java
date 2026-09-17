package com.back.member.app;

import com.back.global.exception.DomainException;
import com.back.member.domain.Member;
import com.back.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryUseCase {
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }
}
