package com.back.shard.post.event;


import com.back.global.dto.PostDto;

public class PostCreatedEvent {
    PostDto postDto;

    public PostCreatedEvent(PostDto post) {
        this.postDto = post;
    }

    public PostDto getPostDto() {
        return this.postDto;
    }
}
