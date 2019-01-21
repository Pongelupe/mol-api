package br.com.mol.molapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mol.molapi.entity.Medicine;
import br.com.mol.molapi.repositories.dao.IMedicineRepository;

public interface MedicineRepository extends JpaRepository<Medicine, String>, IMedicineRepository {

	
	
}
