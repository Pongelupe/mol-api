package br.com.mol.molapi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.user.UserRegisterDTO;
import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.entity.User;
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
	public String registerGetId(UserRegisterDTO userRegisterDTO) {
		return this.register(userRegisterDTO).getId();
	}

	@Override
	public Patient registerPatient(UserRegisterDTO userRegisterDTO) {
		return register(userRegisterDTO);
	}

	private Patient register(UserRegisterDTO userRegisterDTO) {
		Patient patient = new Patient();
		patient = (Patient) userManager.prepareNewUser(patient, userRegisterDTO);

		return patientRepository.save(patient);
	}

	@Override
	public Optional<Patient> findById(String idPatient) {
		return patientRepository.findById(idPatient);
	}

	@Override
	public Optional<Patient> findByCpf(String cpf) {
		return patientRepository.findByCpf(cpf);
	}

	@Override
	public boolean existsById(String id) {
		return patientRepository.existsById(id);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return patientRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return patientRepository.existsByEmail(email);
	}
}
