package com.back.shard.member.out;

import com.back.global.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class MemberApiClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/api/v1/member")
            .build();

    public String getRandomSecureTip() {
        return restClient.get()
                .uri("/members/randomSecureTip")
                .retrieve()
                .body(String.class);
    }

    public MemberDto getMember(Integer memberId) {
        return restClient.get()
                .uri("/members/" + memberId)
                .retrieve()
                .body(MemberDto.class);
    }
}
