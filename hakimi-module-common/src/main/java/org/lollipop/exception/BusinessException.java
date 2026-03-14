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
        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = 500;
    }

    public Integer getCode() {
        return code;
    }
}
