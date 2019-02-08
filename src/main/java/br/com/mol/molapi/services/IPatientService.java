package br.com.mol.molapi.services;

import java.util.Optional;

import br.com.mol.molapi.dtos.user.UserRegisterDTO;
import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.entity.User;

public interface IPatientService {

	String registerGetId(UserRegisterDTO userRegisterDTO);

	Patient registerPatient(UserRegisterDTO userRegisterDTO);

	Optional<Patient> findById(String idPatient);

	boolean existsById(String id);

	Optional<Patient> findByCpf(String cpf);
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
