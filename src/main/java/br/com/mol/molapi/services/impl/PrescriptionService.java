package br.com.mol.molapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mol.molapi.dtos.prescription.PrescriptionDTO;
import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.exceptions.GenericIdException;
import br.com.mol.molapi.repositories.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	public Prescription create(PrescriptionDTO prescriptionDTO) throws GenericIdException {
		if(prescriptionDTO.getId() != null && existsById(prescriptionDTO.getId()))
			throw new GenericIdException("Prescription with id: " 
					+ prescriptionDTO.getId() + "already exists.");
		
		Prescription prescription = mapper.convertValue(prescriptionDTO, Prescription.class);
		
		prescription = prescriptionRepository.save(prescription);
		
		return prescription;
	}
	
	public Prescription findById(String id) {
		//implementar...
		return null;
	}
	
	public Boolean existsById(String id) {
		return prescriptionRepository.existsById(id);
	}
}
