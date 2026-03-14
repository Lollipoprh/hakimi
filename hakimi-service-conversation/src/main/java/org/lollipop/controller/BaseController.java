package org.lollipop.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class BaseController {

    @RequestMapping("/test")
    public String test() {
        return "request success";
    }
}
