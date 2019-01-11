package br.com.mol.molapi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.validators.enums.ValidatorsEnum;

@Component
public class UserRegisterValidator extends BaseValidator<UserRegisterDTO> {

	private StringValidator stringValidator;

	public UserRegisterValidator() {
		super(UserRegisterDTO.class);
		stringValidator = new StringValidator();
	}

	@Override
	public void validate(Errors errors) {
		stringValidator.validateBlank("name", target.getName(), errors);
		stringValidator.validateBlank("email", target.getEmail(), errors);
		stringValidator.validateBlank("cpf", target.getCpf(), errors);
		if (target.getBirthDate() == null) {
			errors.rejectValue("birthDate", ValidatorsEnum.MISSING_FIELD.getFormattedMessage("birthDate"));
		}
		if (target.getGender() == null) {
			errors.rejectValue("gender", ValidatorsEnum.MISSING_FIELD.getFormattedMessage("gender"));
		}

	}

}
