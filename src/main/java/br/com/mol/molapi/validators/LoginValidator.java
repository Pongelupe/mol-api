package br.com.mol.molapi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.mol.molapi.dtos.LoginDTO;

@Component
public class LoginValidator extends BaseValidator<LoginDTO> {

	private StringValidator stringValidator;

	public LoginValidator() {
		super(LoginDTO.class);
		stringValidator = new StringValidator();
	}

	@Override
	public void validate(Errors errors) {
		stringValidator.validateBlank("email", target.getEmail(), errors);
		stringValidator.validateBlank("password", target.getPassword(), errors);
	}

}
