package org.lollipop.config;

import org.lollipop.HttpResponse;
import org.lollipop.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: lollipop
 * @Date: 2026/3/14 12:52
 * @Description:
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public HttpResponse<?> handleBusinessException(BusinessException e) {
        logger.warn("业务异常：{}", e.getMessage());
        System.out.println(e.getMessage());
        return HttpResponse.fail(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        logger.warn("参数校验失败：{}", errorMessage);
        return HttpResponse.fail(400, e.getMessage(), errorMessage);
    }

    @ExceptionHandler(BindException.class)
    public HttpResponse<?> handleBindException(BindException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        logger.warn("参数绑定失败：{}", errorMessage);
        return HttpResponse.fail(400, "参数绑定失败：{}" + e.getMessage(), errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpResponse<?> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.warn("参数非法异常：{}", e.getMessage());
        return HttpResponse.fail(400, "参数非法异常：{}" + e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public HttpResponse<?> handleException(Exception e) {
        logger.error("系统内部异常：{}", e.getMessage(), e);
        return HttpResponse.fail(500, e.getMessage(), Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler(Throwable.class)
    public HttpResponse<?> handleThrowable(Throwable e) {
        logger.error("严重异常：{}", e.getMessage(), e);
        return HttpResponse.fail(500, e.getMessage(), Arrays.toString(e.getStackTrace()));
    }
}
