package br.com.mol.molapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.entity.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String>{
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
