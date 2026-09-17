package com.back.domain.event;


import com.back.domain.dto.PostDto;

public class PostCreateEvent {
    PostDto postDto;

    public PostCreateEvent(PostDto post) {
        this.postDto = post;
    }

    public PostDto getPostDto() {
        return this.postDto;
    }
}
