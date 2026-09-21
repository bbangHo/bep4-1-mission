package com.back.boundedContext.market.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.boundedContext.shard.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketMemberUseCase {
    private final MarketMemberRepository marketMemberRepository;

    @Transactional
    public MarketMember syncMember(MemberDto memberDto) {
        MarketMember member = marketMemberRepository.save(
                new MarketMember(
                        memberDto.getId(),
                        memberDto.getUsername(),
                        "",
                        memberDto.getNickname(),
                        memberDto.getCreateDate(),
                        memberDto.getModifyDate(),
                        memberDto.getActivityScore()
                )
        );

        return member;
    }
}
