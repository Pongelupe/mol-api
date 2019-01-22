package br.com.mol.molapi.services;

import java.util.List;
import java.util.Optional;

import br.com.mol.molapi.dtos.user.UserRegisterDTO;
import br.com.mol.molapi.entity.Patient;

public interface IPatientService {

	String register(UserRegisterDTO userRegisterDTO);

	Optional<Patient> findById(String idPatient);

	List<Patient> findAll();

	boolean existsById(String id);

}
