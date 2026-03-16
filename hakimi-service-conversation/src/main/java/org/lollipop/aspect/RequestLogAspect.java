package org.lollipop.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lollipop
 * @Date: 2026/3/15 12:45
 * @Description:
 **/
@Aspect
@Component
public class RequestLogAspect {

    private static final Logger requestLogger = LoggerFactory.getLogger("REQUEST_LOG");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void webControllerPointcut() {
    }

    @Around("webControllerPointcut()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        String requestId = java.util.UUID.randomUUID().toString();

        long startTime = System.currentTimeMillis();

        Map<String, Object> requestInfo = new HashMap<>();
        requestInfo.put("requestId", requestId);
        requestInfo.put("method", request.getMethod());
        requestInfo.put("url", request.getRequestURI());
        requestInfo.put("queryParams", request.getQueryString());
        requestInfo.put("remoteAddr", request.getRemoteAddr());
        requestInfo.put("userAgent", request.getHeader("User-Agent"));

        try {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                Map<String, Object> params = new HashMap<>();
                for (int i = 0; i < args.length; i++) {
                    params.put("arg" + i, args[i]);
                }
                try {
                    requestInfo.put("params", objectMapper.writeValueAsString(params));
                } catch (Exception e) {
                    requestInfo.put("params", "无法序列化参数");
                }
            }
        } catch (Exception e) {
            requestInfo.put("params", "获取参数失败：" + e.getMessage());
        }

        requestLogger.info("【请求开始】{}", toJson(requestInfo));

        Object result;
        try {
            result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            Map<String, Object> responseInfo = new HashMap<>();
            responseInfo.put("requestId", requestId);
            responseInfo.put("costTime", (endTime - startTime) + "ms");
            responseInfo.put("status", "SUCCESS");

            try {
                responseInfo.put("response", objectMapper.writeValueAsString(result));
            } catch (Exception e) {
                responseInfo.put("response", "无法序列化响应");
            }

            requestLogger.info("【请求结束】{}", toJson(responseInfo));
            return result;

        } catch (Throwable e) {
            long endTime = System.currentTimeMillis();

            Map<String, Object> errorInfo = new HashMap<>();
            errorInfo.put("requestId", requestId);
            errorInfo.put("costTime", (endTime - startTime) + "ms");
            errorInfo.put("status", "ERROR");
            errorInfo.put("exception", e.getClass().getName());
            errorInfo.put("message", e.getMessage());

            requestLogger.error("【请求异常】{}", toJson(errorInfo), e);
            throw e;
        }
    }

    @AfterThrowing(pointcut = "webControllerPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            requestLogger.error("【Controller 异常】{} {} - {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    e.getMessage(),
                    e);
        }
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return String.valueOf(obj);
        }
    }
}