package com.back.post.app;

import com.back.shard.member.dto.MemberDto;
import com.back.global.response.RsData;
import com.back.member.domain.Member;
import com.back.post.domain.Post;
import com.back.post.domain.PostMember;
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
        return postWriteUseCase.write(author, title, content);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Transactional
    public RsData<PostMember> syncMember(MemberDto memberDto) {
        return postWriteUseCase.syncMember(memberDto);
    }

}
