package br.com.mol.molapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.repositories.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor saveDoctor() {
		return null;
	}
}
