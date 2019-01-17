package br.com.mol.molapi.services;

import java.util.List;

import br.com.mol.molapi.dtos.medicine.AutoCompleteDTO;

public interface IMedicineService {
	
	List<AutoCompleteDTO> searchQueryForAutoComplete(String query);

}
