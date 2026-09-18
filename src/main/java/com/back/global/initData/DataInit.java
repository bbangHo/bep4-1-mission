package com.back.global.initData;

import com.back.global.response.RsData;
import com.back.member.app.MemberFacade;
import com.back.member.app.MemberJoinUseCase;
import com.back.member.domain.Member;
import com.back.post.app.PostFacade;
import com.back.post.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DataInit {
    private final DataInit self;
    private final MemberFacade memberFacade;
    private final MemberJoinUseCase memberJoinUseCase;
    private final PostFacade postFacade;

    public DataInit(
            @Lazy DataInit self,
            MemberFacade memberQueryUseCase,
            MemberJoinUseCase memberJoinUseCase,
            PostFacade postFacade
    ) {
        this.self = self;
        this.memberFacade = memberQueryUseCase;
        this.memberJoinUseCase = memberJoinUseCase;
        this.postFacade = postFacade;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePosts();
            self.makeBasePostComments();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberFacade.count() > 0) return;

        RsData<Member> systemMember = memberJoinUseCase.join("system", "1234", "시스템");
        RsData<Member> holdingMember = memberJoinUseCase.join("holding", "1234", "홀딩");
        RsData<Member> adminMember = memberJoinUseCase.join("admin", "1234", "관리자");
        RsData<Member> user1Member = memberJoinUseCase.join("user1", "1234", "유저1");
        RsData<Member> user2Member = memberJoinUseCase.join("user2", "1234", "유저2");
        RsData<Member> user3Member = memberJoinUseCase.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePosts() {
        if (postFacade.count() > 0) return;

        Member user1Member = memberFacade.findByUsername("user1").get();
        Member user2Member = memberFacade.findByUsername("user2").get();
        Member user3Member = memberFacade.findByUsername("user3").get();

        RsData<Post> post1RsData = postFacade.write(user1Member, "제목1", "내용1");
        log.debug(post1RsData.getMessage());

        RsData<Post> post2RsData = postFacade.write(user1Member, "제목2", "내용2");
        log.debug(post2RsData.getMessage());

        RsData<Post> post3RsData = postFacade.write(user1Member, "제목3", "내용3");
        log.debug(post3RsData.getMessage());

        RsData<Post> post4RsData = postFacade.write(user2Member, "제목4", "내용4");
        log.debug(post4RsData.getMessage());

        RsData<Post> post5RsData = postFacade.write(user2Member, "제목5", "내용5");
        log.debug(post5RsData.getMessage());

        RsData<Post> post6RsData = postFacade.write(user3Member, "제목6", "내용6");
        log.debug(post6RsData.getMessage());
    }

    @Transactional
    public void makeBasePostComments() {
        Post post1 = postFacade.findById(1).get();
        Post post2 = postFacade.findById(2).get();
        Post post3 = postFacade.findById(3).get();
        Post post4 = postFacade.findById(4).get();
        Post post5 = postFacade.findById(5).get();
        Post post6 = postFacade.findById(6).get();

        Member user1Member = memberFacade.findByUsername("user1").get();
        Member user2Member = memberFacade.findByUsername("user2").get();
        Member user3Member = memberFacade.findByUsername("user3").get();

        if (post1.hasComments()) return;

        post1.addComment(user1Member, "댓글1");
        post1.addComment(user2Member, "댓글2");
        post1.addComment(user3Member, "댓글3");

        post2.addComment(user2Member, "댓글4");
        post2.addComment(user2Member, "댓글5");

        post3.addComment(user3Member, "댓글6");
        post3.addComment(user3Member, "댓글7");

        post4.addComment(user1Member, "댓글8");
    }
}