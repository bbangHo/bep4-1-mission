package com.back.global.event;


import com.back.boundedContext.post.dto.PostCommentDto;

public class CommentCreateEvent {
    PostCommentDto postCommentDto;

    public CommentCreateEvent(PostCommentDto post) {
        this.postCommentDto = post;
    }

    public PostCommentDto getPostCommentDto() {
        return this.postCommentDto;
    }
}
