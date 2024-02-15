package com.hackerspace2024.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {
    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }
}