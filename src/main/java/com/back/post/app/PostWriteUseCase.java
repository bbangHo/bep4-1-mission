package com.back.post.app;

import com.back.global.dto.PostDto;
import com.back.global.event.PostCreateEvent;
import com.back.global.response.RsData;
import com.back.member.app.MemberFacade;
import com.back.member.domain.Member;
import com.back.member.in.MemberEventListener;
import com.back.member.out.MemberApiClient;
import com.back.post.domain.Post;
import com.back.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final MemberFacade memberFacade;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public RsData<Post> write(Member author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));

        // 게시글 작성시 활동점수 3점 추가
        publisher.publishEvent(new PostCreateEvent(new PostDto(post)));

        String randomSecureTip = memberFacade.getRandomSecureTip();

        return new RsData<>(
                "201-1",
                "%d번 글이 생성되었습니다. 보안 팁 : %s"
                        .formatted(post.getId(), randomSecureTip),
                post
        );
    }
}
