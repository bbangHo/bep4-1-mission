package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.shard.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketMemberFacade {
    private final MarketMemberUseCase memberUseCase;

    @Transactional
    public MarketMember syncMember(MemberDto member) {
        return memberUseCase.syncMember(member);
    }
}
