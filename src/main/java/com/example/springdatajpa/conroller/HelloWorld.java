package com.example.springdatajpa.conroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String helloWorld(){
        return "Welcome to Application";
    }
}
