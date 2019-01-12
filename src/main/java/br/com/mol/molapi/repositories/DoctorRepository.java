package br.com.mol.molapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String>{
	Optional<Doctor> findByEmail(String email);
}
