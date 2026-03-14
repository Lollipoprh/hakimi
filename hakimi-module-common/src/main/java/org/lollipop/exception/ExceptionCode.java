package org.lollipop.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lollipop
 * @Date: 2026/3/12 22:18
 * @Description:=
 **/
public enum ExceptionCode {

    用户未登录(401, "成功");

    private final Integer code;

    private final String message;

    private static final Map<Integer, ExceptionCode> codeMap = new HashMap<>();

    static {
        for (ExceptionCode exceptionCode : ExceptionCode.values()) {
            codeMap.put(exceptionCode.getCode(), exceptionCode);
        }
    }

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 根据错误码获取枚举
     *
     * @param code 错误码
     * @return 对应的异常码枚举，如果不存在则返回 null
     */
    public static ExceptionCode getEnumByCode(Integer code) {
        return codeMap.get(code);
    }
}
