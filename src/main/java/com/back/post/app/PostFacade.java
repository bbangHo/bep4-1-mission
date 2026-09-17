package com.back.post.app;

import com.back.member.domain.Member;
import com.back.global.dto.PostDto;
import com.back.post.domain.Post;
import com.back.global.event.PostCreateEvent;
import com.back.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostFacade {
    private final PostRepository postRepository;
    private final ApplicationEventPublisher publisher;

    public long count() {
        return postRepository.count();
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}
