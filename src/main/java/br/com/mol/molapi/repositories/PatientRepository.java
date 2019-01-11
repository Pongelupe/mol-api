package br.com.mol.molapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
