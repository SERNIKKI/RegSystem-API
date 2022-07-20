package com.bs.regsystemapi.configuration;

import com.bs.regsystemapi.enumeration.common.RespStatus;
import com.bs.regsystemapi.exception.ManageException;
import com.bs.regsystemapi.utils.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage());
        }
        String msg = sb.toString();
        return ResponseResult.error(HttpStatus.BAD_REQUEST.value(), msg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseResult handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseResult.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({ManageException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseResult handleManageException(ManageException ex) {
        return ResponseResult.error(RespStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseResult handleManageException(Exception ex) {
        ex.printStackTrace();
        return ResponseResult.error(RespStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
