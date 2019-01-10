package br.com.mol.molapi.controllers;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/doctors")
public class DoctorsController {
	
	@PostMapping
	public RequestEntity<?> registerDoctor() {
		return null;
	}
}
