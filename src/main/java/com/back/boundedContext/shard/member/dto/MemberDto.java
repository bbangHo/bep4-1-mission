package com.back.boundedContext.shard.member.dto;

import com.back.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberDto {
    private final Integer id;
    private final String username;
    private final String nickname;
    private final int activityScore;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;

    public MemberDto(Member member) {
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
