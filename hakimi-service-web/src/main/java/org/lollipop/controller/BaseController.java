package org.lollipop.controller;

import jakarta.validation.constraints.NotNull;
import org.lollipop.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class BaseController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/.well-known/appspecific/com.chrome.devtools.json")
    public ResponseEntity<Void> devtoolsJson() {
        // 返回204 No Content，表示资源存在但无内容，或者也可以返回200和空对象
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/test")
    public String test() {
        return "request success";
    }

    @RequestMapping("/testRedis")
    public String testRedis() {
        // 存储数据
        redisTemplate.opsForValue().set("key", "12312312312");

        // 获取数据
        Object value = redisTemplate.opsForValue().get("key");
        return value.toString();
    }

    @RequestMapping("/testException")
    public Object test(@NotNull(message = "type不能为空") Integer type) {
        if (type == 1) {
            throw new BusinessException("业务异常");
        } else if (type == 2) {
            int i = 1 / 0;
            return i;
        }
        return "skip";
    }
}
//hakimi-service-web-0.0.1-SNAPSHOT.jar
