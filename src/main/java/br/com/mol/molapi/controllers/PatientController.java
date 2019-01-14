package br.com.mol.molapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.mol.molapi.dtos.UserDTO;
import br.com.mol.molapi.dtos.UserRegisterDTO;
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
	public ResponseEntity<String> addPacient(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
		String idPatient = patientService.add(userRegisterDTO);
		return ResponseEntity.ok(idPatient);
	}

	@GetMapping("{idPatient}")
	public ResponseEntity<Patient> findPatient(@PathVariable String idPatient) {
		Optional<Patient> patient = patientService.findById(idPatient);
		return ResponseEntity.ok(patient.orElseThrow(() -> new EntityNotFoundException("Patient")));
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAllPatients() {
		List<Patient> patients = patientService.findAll();
		return ResponseEntity.ok(patients.stream().map(UserDTO::new).collect(Collectors.toList()));
	}

}
