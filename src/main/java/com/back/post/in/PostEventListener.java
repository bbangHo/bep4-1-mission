package com.back.post.in;

import com.back.shard.member.dto.MemberDto;
import com.back.shard.member.event.MemberJoinedEvent;
import com.back.shard.post.event.PostCreatedEvent;
import com.back.shard.member.out.MemberApiClient;
import com.back.post.app.PostFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostEventListener {
    private final PostFacade postFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        postFacade.syncMember(event.getMemberDto());
        log.info("PostEventListener");
    }
}
