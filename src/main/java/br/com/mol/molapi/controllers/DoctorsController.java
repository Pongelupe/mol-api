package br.com.mol.molapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.DoctorDTO;
import br.com.mol.molapi.dtos.DoctorRegisterDTO;
import br.com.mol.molapi.exceptions.UserEmailException;
import br.com.mol.molapi.services.impl.DoctorService;

@RestController
@CrossOrigin
@RequestMapping("/doctors")
public class DoctorsController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping
	public ResponseEntity<String> registerDoctor(
			@RequestBody @Valid DoctorRegisterDTO doctorRegisterDTO) throws UserEmailException {
		return new ResponseEntity<String>(doctorService
				.saveDoctor(doctorRegisterDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<DoctorDTO> getDoctor() {
		return null;
	}
}
