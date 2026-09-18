package com.back.boundedContext.shard.member.event;


import com.back.boundedContext.shard.member.dto.MemberDto;

public class MemberJoinedEvent {
    MemberDto memberDto;

    public MemberJoinedEvent(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public MemberDto getMemberDto() {
        return this.memberDto;
    }
}
