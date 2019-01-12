package br.com.mol.molapi.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

import br.com.mol.molapi.validators.enums.ValidatorsEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringValidator {

	public boolean validateBlank(String field, String value, Errors errors) {
		boolean blank = StringUtils.isBlank(value);
		if (blank) {
			errors.rejectValue(field, ValidatorsEnum.MISSING_FIELD.getFormattedMessage(field));
		}
		return !blank;
	}

}
