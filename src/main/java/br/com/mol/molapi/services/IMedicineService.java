package br.com.mol.molapi.services;

import java.util.List;

import br.com.mol.molapi.dtos.medicine.AutoCompleteDTO;
import br.com.mol.molapi.entity.Medicine;

public interface IMedicineService {
	
	List<AutoCompleteDTO> searchQueryForAutoComplete(String query);
	
	Medicine insertMedicine(Medicine medicine);

}
