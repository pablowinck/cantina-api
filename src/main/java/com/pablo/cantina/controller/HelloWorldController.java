package com.pablo.cantina.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    public String retornaHello() {
        return "<h1>" +
                "OLÁ MUNDO!!!!" +
                "</h1>";
    }

}
