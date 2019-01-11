package br.com.mol.molapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.services.IPatientService;
import br.com.mol.molapi.validators.UserRegisterValidator;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService pacientService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserRegisterValidator());
	}
	
	@PostMapping
	public ResponseEntity<String> addPacient(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
		String idPatient = pacientService.add(userRegisterDTO);
		return ResponseEntity.ok(idPatient);
	}
	
}
