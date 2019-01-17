package br.com.mol.molapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/prescriptions")
public class PrescriptionController {
	
	@PostMapping
	public ResponseEntity<?> createPrescription() {
		return new ResponseEntity<>("CREATE PRESCRIPTION!", HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updatePrescription() {
		return ResponseEntity.ok("UPDATE PRESCRIPTION!");
	}
	
	@GetMapping("/{prescriptionId}")
	public ResponseEntity<?> getPrescription(@PathVariable String prescriptionId) {
		return ResponseEntity.ok("GET PRESCRIPTION");
	}
}
