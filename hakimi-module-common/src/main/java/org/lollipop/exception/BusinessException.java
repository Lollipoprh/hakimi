package org.lollipop.exception;

/**
 * @Author: lollipop
 * @Date: 2026/3/14 00:55
 * @Description:
 **/
public class BusinessException extends RuntimeException {

    private Integer code;

    private String message;

    public BusinessException(ExceptionCode exceptionCode, String message) {
        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
        this.message = message;
    }

    public BusinessException(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        this.code = 500;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
