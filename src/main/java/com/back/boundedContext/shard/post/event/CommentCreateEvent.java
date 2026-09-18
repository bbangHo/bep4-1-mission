package com.back.boundedContext.shard.post.event;


import com.back.boundedContext.shard.post.dto.PostCommentDto;

public class CommentCreateEvent {
    PostCommentDto postCommentDto;

    public CommentCreateEvent(PostCommentDto post) {
        this.postCommentDto = post;
    }

    public PostCommentDto getPostCommentDto() {
        return this.postCommentDto;
    }
}
