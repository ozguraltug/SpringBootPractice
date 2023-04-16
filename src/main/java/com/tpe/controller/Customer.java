package com.tpe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/customers")
public class Customer {
    //1-Spring boot u selamlama http://localhost:8080/customers/greet
@GetMapping("/greet")
    public String greetSpringBoot(){
    return "Hello Spring Boot";

}

}
