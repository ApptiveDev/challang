package com.challang.backend.util.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/health-check")
public class HealthCheckController {

    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
