package com.example.firstsb.lib;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Response<T> {
    private final int code;
    private final String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)  // 如果data为null，则不返回
    private final T data;

    // 静态变量定义一些通用的代码和信息
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;
    private static final String DEFAULT_SUCCESS_MSG = "ok";
    private static final String DEFAULT_ERROR_MSG = "error";

    // 私有构造方法，强制使用静态工厂方法
    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功时使用的静态方法
    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }
    public static <T> Response<T> success(T data, String msg) {
        return new Response<>(SUCCESS_CODE, msg, data);
    }
    public static <T> Response<T> successM(String msg) {
        return new Response<>(SUCCESS_CODE, msg, null);
    }
    public static <T> Response<T> success() {
        return new Response<>(SUCCESS_CODE, DEFAULT_SUCCESS_MSG, null);
    }
    // 错误时使用的静态方法
    public static <T> Response<T> error(String msg) {
        return new Response<>(ERROR_CODE, msg, null);
    }
    public static <T> Response<T> error(int code, String msg) {
        return new Response<>(code, msg, null);
    }
}

