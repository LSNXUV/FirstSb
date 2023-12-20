package com.example.firstsb.lib;

import com.example.firstsb.lib.CustomException.FatalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// 异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FatalException.class})
    public Response<Object> handleAllException(Exception ex) {
        return Response.error(ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public Response<Object> handleDefaultException(Exception ex) {
        return Response.error("操作失败");
    }
}
