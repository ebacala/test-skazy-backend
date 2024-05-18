package com.ebacala.healthcheck;

import com.ebacala.responsehelper.JsonMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HealthcheckController {
    @GetMapping("/healthcheck")
    public ResponseEntity<String> getHealthcheck() {
        return ResponseEntity.ok(new JsonMessage("OK").getBody());
    }
}
