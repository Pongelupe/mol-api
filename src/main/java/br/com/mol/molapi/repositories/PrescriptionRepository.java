package br.com.mol.molapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String>{
	Optional<Prescription> findById(String id);
	
	boolean existsById(String id);
}
