package br.com.mol.molapi.services.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mol.molapi.dtos.prescription.PrescriptionDTO;
import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.exceptions.GenericIdException;
import br.com.mol.molapi.repositories.PrescriptionRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Autowired DoctorService doctorService;
	
	@Autowired
	private ObjectMapper mapper;
	
	public String create(PrescriptionDTO prescriptionDTO) throws GenericIdException {
		if(prescriptionDTO.getId() != null && existsById(prescriptionDTO.getId()))
			throw new GenericIdException("Prescription with id: " 
					+ prescriptionDTO.getId() + "already exists.");
		
		Doctor doctor = findDoctor(prescriptionDTO.getDoctorId());
		
		Prescription prescription = mapper.convertValue(prescriptionDTO, Prescription.class);
		
		prescription.setDoctor(doctor);
		
		return prescriptionRepository.save(prescription).getId();
	}
	
	public PrescriptionDTO update(PrescriptionDTO prescriptionDTO) {
		if(!existsById(prescriptionDTO.getId()))
			throw new EntityNotFoundException("Prescription not found");
		
		Doctor doctor = findDoctor(prescriptionDTO.getDoctorId());
		
		Prescription prescription = mapper.convertValue(prescriptionDTO, Prescription.class);
		
		prescription.setDoctor(doctor);
		
		prescription = prescriptionRepository.save(prescription);
		
		return mapper.convertValue(prescription, PrescriptionDTO.class);
	}
	
	private Doctor findDoctor(String doctorId) {
		if(!doctorService.existsById(doctorId))
			throw new EntityNotFoundException("Doctor not found");
		
		return doctorService.findById(doctorId);
	}
	
	public PrescriptionDTO findById(String id) {
		Prescription prescription = prescriptionRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Prescription not found"));
		
		return mapper.convertValue(prescription, PrescriptionDTO.class);
	}
	
	public Boolean existsById(String id) {
		return prescriptionRepository.existsById(id);
	}
}
