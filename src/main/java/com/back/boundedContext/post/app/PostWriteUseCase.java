package com.back.boundedContext.post.app;

import com.back.boundedContext.shard.member.dto.MemberDto;
import com.back.boundedContext.shard.post.dto.PostDto;
import com.back.boundedContext.shard.post.event.PostCreatedEvent;
import com.back.boundedContext.global.response.RsData;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.shard.member.out.MemberApiClient;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostMemberRepository;
import com.back.boundedContext.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final PostMemberRepository postMemberRepository;
    private final MemberApiClient memberApiClient;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public RsData<Post> write(Member author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));

        // 게시글 작성시 활동점수 3점 추가
        publisher.publishEvent(new PostCreatedEvent(new PostDto(post)));

        String randomSecureTip = memberApiClient.getRandomSecureTip();

        return new RsData<>(
                "201-1",
                "%d번 글이 생성되었습니다. 보안 팁 : %s"
                        .formatted(post.getId(), randomSecureTip),
                post
        );
    }

    @Transactional
    public RsData<PostMember> syncMember(MemberDto memberDto) {
        PostMember postMember = postMemberRepository.save(
                new PostMember(
                        memberDto.getId(),
                        memberDto.getUsername(),
                        "",
                        memberDto.getNickname(),
                        memberDto.getCreateDate(),
                        memberDto.getModifyDate(),
                        memberDto.getActivityScore()
                )
        );

        return new RsData<>(
                "201-2",
                "%d번 PostMember가 복사되었습니다.",
                postMember
        );
    }
}
