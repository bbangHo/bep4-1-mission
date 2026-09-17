package com.back.post.domain.event;


import com.back.post.domain.dto.PostCommentDto;

public class CommentCreateEvent {
    PostCommentDto postCommentDto;

    public CommentCreateEvent(PostCommentDto post) {
        this.postCommentDto = post;
    }

    public PostCommentDto getPostCommentDto() {
        return this.postCommentDto;
    }
}
