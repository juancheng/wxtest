package com.hym.wxtest.handler;

import com.hym.wxtest.dto.GlobalExceptionResult;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 异常的统一处理类
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public GlobalExceptionResult handleConstraintViolationException(Exception e, HttpServletRequest request) {
        return new GlobalExceptionResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public GlobalExceptionResult handleMethodArgumentNotValidException(Exception e, HttpServletRequest request) {
        return new GlobalExceptionResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public GlobalExceptionResult handleIllegalArgumentException(Exception e, HttpServletRequest request) {
        return new GlobalExceptionResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public GlobalExceptionResult handleMethodArgumentTypeMismatchException(Exception e, HttpServletRequest request) {
        return new GlobalExceptionResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    public GlobalExceptionResult noHandlerFoundException(Exception e, HttpServletRequest request) {
        return new GlobalExceptionResult(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    public GlobalExceptionResult unknownException(Exception e, HttpServletRequest request) {
        return new GlobalExceptionResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
    }

}
