package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
		
	/**
	 * health check end-point
	 * @return
	 */
	@GetMapping("/healthCheck")
	public ResponseEntity<String> getHealthCheck() {
		return new ResponseEntity<>("Department Server Up", HttpStatus.OK);
	}

}
