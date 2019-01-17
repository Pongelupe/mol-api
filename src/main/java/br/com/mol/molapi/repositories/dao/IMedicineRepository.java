package br.com.mol.molapi.repositories.dao;

import java.util.List;

import br.com.mol.molapi.dtos.medicine.AutoCompleteDTO;

public interface IMedicineRepository {

	List<AutoCompleteDTO> searchQueryForAutoComplete(String query);

}
