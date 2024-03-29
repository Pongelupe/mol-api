package br.com.mol.molapi.controllers;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.user.UserDTO;
import br.com.mol.molapi.dtos.user.UserRegisterDTO;
import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.services.IPatientService;
import br.com.mol.molapi.validators.UserRegisterValidator;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserRegisterValidator());
	}

	@PostMapping
	public ResponseEntity<String> registerPacient(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
		String idPatient = patientService.registerGetId(userRegisterDTO);
		return new ResponseEntity<>(idPatient, HttpStatus.CREATED);
	}

	@GetMapping("{idPatient}")
	public ResponseEntity<UserDTO> findPatientById(@PathVariable String idPatient) {
		Optional<Patient> patient = patientService.findById(idPatient);
		return ResponseEntity.ok(new UserDTO(patient.orElseThrow(() -> new EntityNotFoundException("Patient"))));
	}

	@GetMapping("cpf/{cpf}")
	public ResponseEntity<UserDTO> findPatientByCpf(@PathVariable String cpf) {
		Optional<Patient> patient = patientService.findByCpf(cpf);
		return ResponseEntity.ok(new UserDTO(patient.orElseThrow(() -> new EntityNotFoundException("Patient"))));
	}

}
