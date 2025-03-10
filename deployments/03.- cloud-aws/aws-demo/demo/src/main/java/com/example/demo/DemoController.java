package com.example.demo;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {

	@GetMapping
	public ResponseEntity<String> demo() {
		return ResponseEntity.ok("Demo ok "+LocalDate.now());
	}

}
