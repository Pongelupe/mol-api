package br.com.mol.molapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.medicine.AutoCompleteDTO;
import br.com.mol.molapi.entity.Medicine;
import br.com.mol.molapi.repositories.MedicineRepository;
import br.com.mol.molapi.services.IMedicineService;

@Service
public class MedicineService implements IMedicineService {
	
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<AutoCompleteDTO> searchQueryForAutoComplete(String query) {
		return medicineRepository.searchQueryForAutoComplete(query);
	}

	@Override
	public String insertMedicine(Medicine medicine) {
		return medicineRepository.save(medicine).getId();
	}

}
