package br.com.mol.molapi.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.DoctorDTO;
import br.com.mol.molapi.dtos.DoctorRegisterDTO;
import br.com.mol.molapi.entity.Doctor;
import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.exceptions.UserEmailException;
import br.com.mol.molapi.managers.impl.UserManager;
import br.com.mol.molapi.repositories.DoctorRepository;
import br.com.mol.molapi.utils.DTOConverter;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserManager userManager;

	public String saveDoctor(DoctorRegisterDTO doctorRegisterDTO) throws UserEmailException {
		if (doctorExistsByEmail(doctorRegisterDTO.getEmail()))
			throw new UserEmailException("Doctor with email: " + doctorRegisterDTO.getEmail() + " already exists.");

		Doctor doctor = new Doctor();
		doctor = (Doctor) userManager.prepareNewUser(doctor, doctorRegisterDTO);

		return doctorRepository.save(doctor).getId();
	}

	public Doctor getDoctorById(String id) {
		return doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
	}

	public DoctorDTO getDoctorByEmail(String email) throws UserEmailException {
		Optional<User> doctorOptional = doctorRepository.findByEmail(email);
		if (doctorOptional.isPresent()) {
			Doctor doctor = (Doctor) doctorOptional.get();
			DoctorDTO doctorDTO = new DoctorDTO();
			DTOConverter.mapPropertiesTo(doctor, doctorDTO);
			return doctorDTO;
		} else {
			throw new UserEmailException("Doctor with email: " + email + " doesnt exists.");
		}
	}

	public Boolean doctorExistsByEmail(String email) {
		return doctorRepository.existsByEmail(email);
	}

	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}
}
