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
public class MemberJoinUseCase {
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

    @Transactional
    public Member join(String username, String password, String nickname) {
        findByUsername(username).ifPresent(m -> {
            throw new DomainException("409-1", "이미 존재하는 username 입니다.");
        });

        return memberRepository.save(new Member(username, password, nickname));
    }
}
