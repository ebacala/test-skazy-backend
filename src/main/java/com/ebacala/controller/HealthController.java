package com.ebacala.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HealthController {
    @GetMapping("/healthcheck")
    public String getHealthcheck() {
        return "OK";
    }
}
