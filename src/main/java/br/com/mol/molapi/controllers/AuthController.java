package br.com.mol.molapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mol.molapi.dtos.LoginDTO;
import br.com.mol.molapi.dtos.user.UserDTO;
import br.com.mol.molapi.exceptions.LoginInvalidException;
import br.com.mol.molapi.services.IAuthService;
import br.com.mol.molapi.validators.LoginValidator;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private IAuthService authService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new LoginValidator());
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO) throws LoginInvalidException {
		UserDTO idUser = authService.login(loginDTO).orElseThrow(LoginInvalidException::new);
		return ResponseEntity.ok(idUser);
	}

}
