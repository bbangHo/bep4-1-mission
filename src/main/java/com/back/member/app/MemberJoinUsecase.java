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
public class MemberJoinUsecase {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(String username, String password, String nickname) {
        memberRepository.findByUsername(username).ifPresent(m -> {
            throw new DomainException("409-1", "이미 존재하는 username 입니다.");
        });

        return memberRepository.save(new Member(username, password, nickname));
    }
}
