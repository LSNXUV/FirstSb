package com.example.firstsb.lib;

import com.example.firstsb.lib.CustomException.FatalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// 异常处理器
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FatalException.class})
    public ResponseEntity<Object> handleAllException(Exception ex) {
        ResponseData<Object> responseData = new ResponseData<>(1, ex.getMessage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleDefaultException(Exception ex) {
        ResponseData<Object> responseData = new ResponseData<>(1, "参数错误");
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
