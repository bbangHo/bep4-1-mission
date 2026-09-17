package com.back.global.response;

import lombok.Getter;

@Getter
public enum RsCode {
    POST_CREATE_SUCCESS("200-1", "Post 생성 완료"),
    POST_FETCHED_SUCCESS("200-2", "Post 조회 완료"),

    ;

    private final String code;
    private final String message;

    RsCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
