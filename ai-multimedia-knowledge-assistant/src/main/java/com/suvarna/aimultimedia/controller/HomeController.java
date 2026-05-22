package com.suvarna.aimultimedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "AI Multimedia Knowledge Assistant is running!";
    }
}
