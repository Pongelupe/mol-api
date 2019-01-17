package br.com.mol.molapi.services.impl;

import java.util.List;
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
	public String register(UserRegisterDTO userRegisterDTO) {
		Patient patient = new Patient();
		patient = (Patient) userManager.prepareNewUser(patient, userRegisterDTO);

		return patientRepository.save(patient).getId();
	}

	@Override
	public Optional<Patient> findById(String idPatient) {
		return patientRepository.findById(idPatient);
	}

	@Override
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

}
