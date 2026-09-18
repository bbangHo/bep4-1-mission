package com.back.boundedContext.member.domain;

import com.back.boundedContext.global.GlobalConfig;
import com.back.boundedContext.shard.member.domain.SourceMember;
import com.back.boundedContext.shard.member.dto.MemberDto;
import com.back.boundedContext.shard.member.event.MemberModifiedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.back.boundedContext.global.GlobalConfig.eventPublisher;


@Getter
@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
public class Member extends SourceMember {
    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    public int increaseActivityScore(int amount) {
        if (amount == 0) return getActivityScore();

        setActivityScore(getActivityScore() + amount);
        eventPublisher.publish(new MemberModifiedEvent(new MemberDto(this)));

        return getActivityScore();
    }
}