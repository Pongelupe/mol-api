package br.com.mol.molapi.services.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mol.molapi.dtos.LoginDTO;
import br.com.mol.molapi.dtos.user.UserDTO;
import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.repositories.DoctorRepository;
import br.com.mol.molapi.repositories.PatientRepository;
import br.com.mol.molapi.services.IAuthService;
import br.com.mol.molapi.utils.DTOConverter;

@Service
public class AuthService implements IAuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Optional<UserDTO> login(LoginDTO loginDto) {
		Optional<User> optionalDoctor = doctorRepository.findByEmail(loginDto.getEmail());
		User user = optionalDoctor.orElseGet(() -> patientRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new EntityNotFoundException("User")));
		UserDTO userDTO = new UserDTO();
		DTOConverter.mapPropertiesTo(user, userDTO);

		return passwordEncoder.matches(loginDto.getPassword(), user.getPassword()) ? Optional.of(userDTO)
				: Optional.empty();
	}

}
