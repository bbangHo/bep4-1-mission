package com.back.boundedContext.shard.market.dto;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.in.MarketMemberListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MarketMemberDto {
    private final Integer id;
    private final String username;
    private final String nickname;
    private final int activityScore;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;

    public MarketMemberDto(MarketMember member) {
        this(
                member.getId(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore(),
                member.getCreateDate(),
                member.getModifyDate()
        );
    }
}
