package com.back.shard.member.event;

import com.back.shard.member.dto.MemberDto;

public class MemberModifiedEvent {
    MemberDto memberDto;

    public MemberModifiedEvent(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public MemberDto getMemberDto() {
        return this.memberDto;
    }
}
