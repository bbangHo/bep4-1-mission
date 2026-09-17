package com.back.boundedContext.post.dto;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.boundedContext.post.entity.PostComment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class PostCommentDto {
    private final int id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final int postId;
    private final int authorId;
    private final String authorName;
    private final String content;

    public PostCommentDto(PostComment postComment) {
        this(
                postComment.getId(),
                postComment.getCreateDate(),
                postComment.getModifyDate(),
                postComment.getPost().getId(),
                postComment.getAuthor().getId(),
                postComment.getAuthor().getNickname(),
                postComment.getContent()
        );
    }
}
