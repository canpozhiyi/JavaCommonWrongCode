package com.csd.wrongCode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chensd
 * @Date 2023-12-24
 **/
@RestController
public class HelloWorld {


    @GetMapping("/hello")
    public String hello(){
        return "hello World!";
    }
}
