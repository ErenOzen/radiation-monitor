package com.example.radiationmonitor.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST Controller to provide a simple health check endpoint.
 * This helps to verify that the service is running correctly.
 *
 * @RestController marks this class as a controller where every method returns a
 * domain object instead of a view. It's shorthand for including @Controller and @ResponseBody.
 *
 * @RequestMapping("/api/v1") maps all requests starting with "/api/v1" to this controller.
 */
@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

    /**
     * Handles HTTP GET requests to the /api/v1/health endpoint.
     *
     * @GetMapping("/health") maps this specific method to the path.
     * @return A Map which Spring Web will automatically convert to a JSON object.
     */
    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        // a Map is used since it is easier to create a key-value JSON structure.
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Radiation Monitor service is running!");
        return response;
    }
}
