package br.com.mol.molapi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.managers.impl.UserManager;
import br.com.mol.molapi.repositories.PatientRepository;
import br.com.mol.molapi.services.IPatientService;

@Service
public class PacientService implements IPatientService {

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	UserManager userManager;

	@Override
	public String add(UserRegisterDTO userRegisterDTO) {
		Patient patient = new Patient(userManager.prepareNewUser(userRegisterDTO));
		
		return patientRepository.save(patient).getId();
	}

	@Override
	public Optional<Patient> findById(String idPatient) {
		return patientRepository.findById(idPatient);
	}

}
