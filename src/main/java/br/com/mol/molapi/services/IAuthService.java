package br.com.mol.molapi.services;

import java.util.Optional;

import br.com.mol.molapi.dtos.LoginDTO;

public interface IAuthService {

	
	Optional<String> login(LoginDTO loginDto);
	
}
