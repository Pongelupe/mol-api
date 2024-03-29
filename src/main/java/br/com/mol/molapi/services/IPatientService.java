package br.com.mol.molapi.services;

import java.util.Optional;

import br.com.mol.molapi.dtos.user.UserRegisterDTO;
import br.com.mol.molapi.entity.Patient;

public interface IPatientService {

	String registerGetId(UserRegisterDTO userRegisterDTO);

	Patient registerPatient(UserRegisterDTO userRegisterDTO);

	Optional<Patient> findById(String idPatient);

	boolean existsById(String id);

	Optional<Patient> findByCpf(String cpf);

}
