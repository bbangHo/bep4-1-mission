package com.back.member.domain;

import com.back.global.GlobalConfig;
import com.back.shard.member.domain.SourceMember;
import com.back.shard.member.dto.MemberDto;
import com.back.shard.member.event.MemberModifiedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
public class Member extends SourceMember {
    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    public int increaseActivityScore(int amount) {
        setActivityScore(getActivityScore() + amount);
        GlobalConfig.getEventPublisher().publish(new MemberModifiedEvent(new MemberDto(this)));
        return getActivityScore();
    }
}