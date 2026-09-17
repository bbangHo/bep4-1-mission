package com.back.post.app;

import com.back.global.response.RsData;
import com.back.member.domain.Member;
import com.back.post.domain.Post;
import com.back.post.out.PostRepository;
import lombok.RequiredArgsConstructor;
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

        return new RsData<>("201-1", "%d번 글이 생성되었습니다.".formatted(post.getId()), post);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}
