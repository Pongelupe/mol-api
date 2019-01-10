package br.com.mol.molapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String>{
	Doctor findByEmail(String email);
}
