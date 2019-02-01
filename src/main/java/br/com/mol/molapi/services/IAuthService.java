package br.com.mol.molapi.services;

import java.util.Optional;

import br.com.mol.molapi.dtos.LoginDTO;
import br.com.mol.molapi.dtos.user.UserDTO;

public interface IAuthService {

	
	Optional<UserDTO> login(LoginDTO loginDto);
	
}
