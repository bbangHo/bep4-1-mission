package com.back.boundedContext.post.app;

import com.back.boundedContext.shard.member.dto.MemberDto;
import com.back.boundedContext.global.response.RsData;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostWriteUseCase postWriteUseCase;
    private final PostSupport postSupport;
    private final PostSyncMemberUseCase postSyncMemberUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return postSupport.count();
    }


    @Transactional(readOnly = true)
    public Optional<Post> findById(int id) {
        return postSupport.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<PostMember> findMemberByUsername(String username) {
        return postSupport.findMemberByUsername(username);
    }

    @Transactional
    public RsData<Post> write(PostMember author, String title, String content) {
        return postWriteUseCase.write(author, title, content);
    }

    @Transactional
    public PostMember syncMember(MemberDto memberDto) {
        return postSyncMemberUseCase.syncMember(memberDto);
    }
}
