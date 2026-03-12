package org.lollipop;

/**
 * @Author: lollipop
 * @Date: 2026/3/12 22:15
 * @Description:
 **/
public class HttpResponse<T> {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 响应消息描述
     */
    private String message;

    /**
     * 响应数据载体
     */
    private T data;

    /**
     * 响应时间戳
     */
    private Long timestamp;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
