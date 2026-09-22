package com.back.shard.member.event;


import com.back.shard.member.dto.MemberDto;

public class MemberJoinedEvent {
    MemberDto memberDto;

    public MemberJoinedEvent(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public MemberDto getMemberDto() {
        return this.memberDto;
    }
}
