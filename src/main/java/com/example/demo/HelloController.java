package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello";
    }

    @GetMapping("/introduce")
    public String introduce(@RequestParam(required = false) String name, Model model) {
        if (name == null || name.isBlank()) {
            model.addAttribute("message", "안녕하세요 제 이름은 이재훈입니다!");
        } else {
            model.addAttribute("message", "안녕하세요 제 이름은 " + name + "입니다!");
        }

        return "introduce";
    }
}