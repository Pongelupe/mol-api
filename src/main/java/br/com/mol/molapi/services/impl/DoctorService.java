package br.com.mol.molapi.services.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.DoctorRegisterDTO;
import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.exceptions.UserEmailException;
import br.com.mol.molapi.managers.impl.UserManager;
import br.com.mol.molapi.repositories.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserManager userManager;

	public String saveDoctor(DoctorRegisterDTO doctorRegisterDTO) throws UserEmailException {
		if (getDoctorByEmail(doctorRegisterDTO.getEmail()).isPresent())
			throw new UserEmailException("Doctor with email: " + doctorRegisterDTO.getEmail() + " already exists.");

		Doctor doctor = new Doctor();
		doctor = (Doctor) userManager.prepareNewUser(doctor, doctorRegisterDTO);

		return doctorRepository.save(doctor).getId();
	}

	public Doctor getDoctorById(String id) {
		return doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
	}

	public Optional<User> getDoctorByEmail(String email) {
		return doctorRepository.findByEmail(email);
	}
}
