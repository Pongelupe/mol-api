package br.com.mol.molapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.prescription.PrescriptionDTO;
import br.com.mol.molapi.exceptions.GenericIdException;
import br.com.mol.molapi.services.impl.PrescriptionService;

@RestController
@CrossOrigin
@RequestMapping("/prescriptions")
public class PrescriptionController {
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	@PostMapping
	public ResponseEntity<String> createPrescription(@RequestBody @Valid 
			PrescriptionDTO prescriptionDTO) throws GenericIdException {
		return new ResponseEntity<>(prescriptionService.create(prescriptionDTO), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<PrescriptionDTO> updatePrescription(@RequestBody @Valid 
			PrescriptionDTO prescriptionDTO) {
		return ResponseEntity.ok(prescriptionService.update(prescriptionDTO));
	}
	
	@GetMapping("/{prescriptionId}")
	public ResponseEntity<PrescriptionDTO> getPrescription(@PathVariable String prescriptionId) {
		return ResponseEntity.ok(prescriptionService.findById(prescriptionId));
	}
}
