package com.example.besharefile.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/admin")
    public String testAdmin() {
        return "OK";
    }
}
