package com.back.member.app;

import com.back.global.response.RsData;
import com.back.member.domain.Member;
import com.back.member.domain.MemberPolicy;
import com.back.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberRepository memberRepository;
    private final MemberJoinUseCase memberJoinUsecase;
    private final MemberPolicy memberPolicy;

    @Transactional(readOnly = true)
    public long count() {
        return memberRepository.count();
    }

    @Transactional
    public RsData<Member> join(String username, String password, String nickname) {
        return memberJoinUsecase.join(username, password, nickname);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(int id) {
        return memberRepository.findById(id);
    }

    public String getRandomSecureTip() {
        return "비밀번호 유효기간은 %d입니다.".formatted(memberPolicy.getNeedToChangePasswordDays());
    }
}
