package com.back.post.domain;

import com.back.global.GlobalConfig;
import com.back.shard.post.dto.PostCommentDto;
import com.back.shard.post.event.CommentCreateEvent;
import com.back.global.entity.BaseIdAndTime;
import com.back.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;


@Entity
@Getter
@Table(name = "POST_POST")
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public PostComment addComment(Member author, String content) {
        PostComment postComment = new PostComment(this, author, content);

        comments.add(postComment);

        // 코멘트 작성시 활동점수 1점 추가
        GlobalConfig.getEventPublisher().publish(new CommentCreateEvent(new PostCommentDto(postComment)));

        return postComment;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }
}
