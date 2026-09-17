package com.back.post.app;

import com.back.member.domain.Member;
import com.back.global.dto.PostDto;
import com.back.post.domain.Post;
import com.back.global.event.PostCreateEvent;
import com.back.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher publisher;

    public long count() {
        return postRepository.count();
    }

    public Post write(Member author, String title, String content) {
        Post post = new Post(author, title, content);

        // 게시글 작성시 활동점수 3점 추가
        publisher.publishEvent(new PostCreateEvent(new PostDto(post)));

        return postRepository.save(post);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}
