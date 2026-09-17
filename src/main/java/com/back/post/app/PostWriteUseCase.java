package com.back.post.app;

import com.back.global.dto.PostDto;
import com.back.global.event.PostCreateEvent;
import com.back.member.domain.Member;
import com.back.post.domain.Post;
import com.back.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Post write(Member author, String title, String content) {
        Post post = new Post(author, title, content);

        // 게시글 작성시 활동점수 3점 추가
        publisher.publishEvent(new PostCreateEvent(new PostDto(post)));

        return postRepository.save(post);
    }
}
