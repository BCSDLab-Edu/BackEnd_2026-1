package com.example.demo;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JsonController {

    @GetMapping("/json")
    public Json getUser() {
        return new Json(26,"허준기");
    }
}
