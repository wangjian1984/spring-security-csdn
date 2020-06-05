package com.greentran.springsecuritycsdn.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method= RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/me",method= RequestMethod.GET)
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}