package br.com.mol.molapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Patient;
import br.com.mol.molapi.entity.User;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

	Optional<User> findByEmail(String email);

	Optional<Patient> findByCpf(String cpf);

}
