package com.back.global.event;


import com.back.global.dto.PostDto;

public class PostCreateEvent {
    PostDto postDto;

    public PostCreateEvent(PostDto post) {
        this.postDto = post;
    }

    public PostDto getPostDto() {
        return this.postDto;
    }
}
