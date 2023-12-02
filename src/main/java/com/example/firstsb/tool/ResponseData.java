package com.example.firstsb.tool;

import lombok.Getter;

@Getter
public class ResponseData<T> {
    private final int code;
    private final String msg;
    private final T data;
    private static final int DEFAULT_CODE = 0;
    private static final String DEFAULT_MSG = "ok";
    public ResponseData(){
        this(DEFAULT_CODE, DEFAULT_MSG, null);
    }
    public ResponseData(String msg) {
        this(DEFAULT_CODE, msg, null);
    }
    public ResponseData(int code, String msg) {
        this(code, msg, null);
    }
    public ResponseData(T data) {
        this(DEFAULT_CODE, DEFAULT_MSG, data);
    }
    public ResponseData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}