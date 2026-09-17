package com.back.in.eventListener;

import com.back.domain.Member;
import com.back.app.usecase.MemberService;
import com.back.domain.event.CommentCreateEvent;
import com.back.domain.event.PostCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

/**
 * 이벤트 리스너는 외부/내부에서 발생한 신호(이벤트)를 감지하여 시스템 내부로 작업을 트리거하는 역할을 하기에 in에 배치하는 것
 */
@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberService memberService;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreateEvent event) {
        Member member = memberService.findById(event.getPostDto().getAuthorId()).get();

        member.increaseActivityScore(3);
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CommentCreateEvent event) {
        Member member = memberService.findById(event.getPostCommentDto().getAuthorId()).get();

        member.increaseActivityScore(1);
    }
}
