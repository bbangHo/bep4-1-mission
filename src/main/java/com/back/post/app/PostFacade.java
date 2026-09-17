package com.back.post.app;

import com.back.global.response.RsCode;
import com.back.global.response.RsData;
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

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostWriteUseCase postWriteUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    @Transactional
    public RsData<Post> write(Member author, String title, String content) {
        Post post = postWriteUseCase.write(author, title, content);

        return new RsData<>(RsCode.POST_CREATE_SUCCESS, post);
    }

    @Transactional(readOnly = true)
    public RsData<Post> findById(int id) {
        Post post = postRepository.findById(id).get();
        return new RsData<>(RsCode.POST_FETCHED_SUCCESS, post);
    }
}
