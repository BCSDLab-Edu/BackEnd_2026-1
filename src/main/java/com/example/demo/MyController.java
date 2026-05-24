package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import java.util.HashMap;

@Controller
public class MyController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @ResponseBody
    @GetMapping("/introduce")
    public String introduce(@RequestParam(required = false, defaultValue = "김도완") String name) {
        return "안녕하세요 제 이름은 " + name + "입니다!";
    }

    @ResponseBody
    @GetMapping("/json")
    public Map<String, Object> json() {

        Map<String, Object> map = new HashMap<>();

        map.put("age", 23);
        map.put("name", "김도완");

        return map;
    }
}
