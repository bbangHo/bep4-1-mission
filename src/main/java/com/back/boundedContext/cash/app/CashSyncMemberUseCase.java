package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.boundedContext.global.eventPublisher.EventPublisher;
import com.back.boundedContext.shard.cash.dto.CashMemberDto;
import com.back.boundedContext.shard.cash.event.CashMemberCreatedEvent;
import com.back.boundedContext.shard.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final EventPublisher eventPublisher;


    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        boolean isNew = !cashMemberRepository.existsById(memberDto.getId());

        CashMember member = cashMemberRepository.save(
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

        if(isNew) {
            eventPublisher.publish(new CashMemberCreatedEvent(new CashMemberDto(member)));
        }

        return member;
    }
}
