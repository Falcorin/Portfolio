package ru.ex.autorisation.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/withSecurity")
    public String goWithNotAuthorization(){
        return "Yes";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "it`s login page";
    }
}
