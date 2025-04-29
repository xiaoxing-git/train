package com.xiaoxing.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {
    @GetMapping("/hello")
    public String doHello(){
        return "hello!";
    }
}
