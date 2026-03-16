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
     * 扩展消息
     */
    private String exdMsg;

    /**
     * 响应时间戳
     */
    private Long timestamp;

    public HttpResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public HttpResponse(Integer code, String message, T data, String exdMsg) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.exdMsg = exdMsg;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> HttpResponse<T> success() {
        return success(null);
    }

    public static <T> HttpResponse<T> success(T data) {
        return new HttpResponse(200, "success", data, null);
    }

    public static HttpResponse fail() {
        return new HttpResponse(500, "fail", null, null);
    }

    public static HttpResponse fail(Integer code, String message, String exdMsg) {
        return new HttpResponse(code, message, null, exdMsg);
    }

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
