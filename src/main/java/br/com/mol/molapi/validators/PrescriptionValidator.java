package br.com.mol.molapi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import br.com.mol.molapi.dtos.prescription.PrescriptionDTO;

@Component
public class PrescriptionValidator extends BaseValidator<PrescriptionDTO> {
	
	private StringValidator stringValidator;
	
	public PrescriptionValidator() {
		super(PrescriptionDTO.class);
		stringValidator = new StringValidator();
	}

	@Override
	public void validate(Errors errors) {
		stringValidator.validateBlank("doctorId", target.getDoctorId(), errors);
	}
}
