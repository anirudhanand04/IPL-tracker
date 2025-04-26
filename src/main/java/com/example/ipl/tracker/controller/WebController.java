package com.example.ipl.tracker.controller;

import org.springframework.stereotype.Controller;

@Controller
public class WebController {
    @GetMapping("/")
    public String index() {
        return "index";  // This resolves to templates/index.html
    }
}
