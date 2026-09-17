package com.back.post.domain.event;


import com.back.post.domain.dto.PostDto;

public class PostCreateEvent {
    PostDto postDto;

    public PostCreateEvent(PostDto post) {
        this.postDto = post;
    }

    public PostDto getPostDto() {
        return this.postDto;
    }
}
