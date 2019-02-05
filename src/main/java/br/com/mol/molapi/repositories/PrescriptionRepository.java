package br.com.mol.molapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.repositories.dao.IPrescriptionRepository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, String>, IPrescriptionRepository {
	Optional<Prescription> findById(String id);

	boolean existsById(String id);

	List<Prescription> findByPatientId(String patientId);
}
