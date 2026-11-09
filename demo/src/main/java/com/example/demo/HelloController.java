package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!!!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello";
    }

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce.html";
    }

    @GetMapping("/introduce1")
    public String introduce1(@RequestParam(defaultValue = "김도완") String name, Model model) {
        model.addAttribute("name", name);
        return "introduce1";
    }
    @ResponseBody
    @GetMapping("/json")
    public Person json() {
        return new Person(23, "김도완");
    }

}
