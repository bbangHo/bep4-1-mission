package com.back.global.response;

import lombok.Getter;

@Getter
public class RsData<T> {
    public RsCode code;
    public String message;
    public T result;

    public RsData (RsCode code, T result) {
        this.code = code;
        this.message = code.name();
        this.result = result;
    }
}
