package com.ebacala.healthcheck;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HealthcheckController {
    @GetMapping("/healthcheck")
    public String getHealthcheck() {
        return "OK";
    }
}
