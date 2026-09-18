package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.shard.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        return cashMemberRepository.save(
                new CashMember(
                        memberDto.getId(),
                        memberDto.getUsername(),
                        "",
                        memberDto.getNickname(),
                        memberDto.getCreateDate(),
                        memberDto.getModifyDate(),
                        memberDto.getActivityScore()
                )
        );
    }
}
