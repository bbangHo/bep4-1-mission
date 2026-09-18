package com.back.boundedContext.global.response;

import lombok.Getter;

@Getter
public class RsData<T> {
    public String code;
    public String message;
    public T result;

    public RsData (String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
}
