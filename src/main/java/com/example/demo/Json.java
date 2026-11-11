package com.example.demo;

import com.fasterxml.jackson.annotation.JsonView;

public class Json {

    private String username;
    private int age;

    public Json() {
    }

    public Json(int age, String username) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return this.username;
    }

    public int getAge(){
        return this.age;
    }
}
