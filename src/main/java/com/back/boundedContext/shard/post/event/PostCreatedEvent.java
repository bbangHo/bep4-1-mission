package com.back.boundedContext.shard.post.event;


import com.back.boundedContext.shard.post.dto.PostDto;

public class PostCreatedEvent {
    PostDto postDto;

    public PostCreatedEvent(PostDto post) {
        this.postDto = post;
    }

    public PostDto getPostDto() {
        return this.postDto;
    }
}
