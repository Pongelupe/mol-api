package br.com.mol.molapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.medicine.AutoCompleteDTO;
import br.com.mol.molapi.services.IMedicineService;

@RestController
@CrossOrigin
@RequestMapping("/medicines")
public class MedicineController {

	@Autowired
	private IMedicineService medicineService;
	
	@GetMapping
	public List<AutoCompleteDTO> searchForAutoComplte(@RequestParam("q") String query) {
		return medicineService.searchQueryForAutoComplete(query);
	}
	
}
