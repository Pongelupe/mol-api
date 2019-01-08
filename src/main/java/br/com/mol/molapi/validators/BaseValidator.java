package br.com.mol.molapi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public abstract class BaseValidator<T> implements Validator {

	protected T target;
	private final Class<T> typeParameterClass;
	
	public BaseValidator(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	@Override
	public final boolean supports(Class<?> clazz) {
		return typeParameterClass.isAssignableFrom(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final void validate(Object target, Errors errors) {
		this.target = (T) target;
		validate(errors);
	}

	public abstract void validate(Errors errors);

}
