package br.com.mol.molapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.services.IPatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService pacientService; 
	
	@PostMapping
	public ResponseEntity<String> addPacient(@RequestBody UserRegisterDTO userRegisterDTO) {
		String idPatient = pacientService.add(userRegisterDTO);
		return ResponseEntity.ok(idPatient);
	}
	
}
