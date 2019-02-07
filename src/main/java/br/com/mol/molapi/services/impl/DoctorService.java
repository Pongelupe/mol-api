package br.com.mol.molapi.services.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.user.DoctorDTO;
import br.com.mol.molapi.dtos.user.DoctorRegisterDTO;
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

	public String register(DoctorRegisterDTO doctorRegisterDTO) throws UserEmailException {
		if (existsByEmail(doctorRegisterDTO.getEmail()))
			throw new UserEmailException("Doctor with email: " + doctorRegisterDTO.getEmail() + " already exists.");

		Doctor doctor = new Doctor();
		doctor = (Doctor) userManager.prepareNewUser(doctor, doctorRegisterDTO);
		doctor.setDigitalSignature(Base64.decodeBase64(doctorRegisterDTO.getDigitalSignatureBase64().getBytes()));
		
		return doctorRepository.save(doctor).getId();
	}

	public Doctor findById(String id) {
		return doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
	}
	
	public DoctorDTO findByIdDTO(String id) {
		return (DoctorDTO) doctorRepository.findById(id)
				.map(d -> DTOConverter.mapPropertiesTo(d, new DoctorDTO()))
				.orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
	}

	public DoctorDTO findByEmail(String email) throws UserEmailException {
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

	public Boolean existsByEmail(String email) {
		return doctorRepository.existsByEmail(email);
	}
	
	public DoctorDTO findByCpf(String cpf) throws UserEmailException {
		Optional<User> doctorOptional = doctorRepository.findByCpf(cpf);
		Doctor doctor = (Doctor) doctorOptional
				.orElseThrow(() -> new UserEmailException("Doctor with cpf: " + cpf + " doesnt exists."));
		DoctorDTO doctorDTO = new DoctorDTO();
		DTOConverter.mapPropertiesTo(doctor, doctorDTO);
		return doctorDTO;
	}
	
	public Boolean existsById(String id) {
		return doctorRepository.existsById(id);
	}
}
