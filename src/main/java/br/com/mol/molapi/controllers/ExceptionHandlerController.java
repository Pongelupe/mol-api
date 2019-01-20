package br.com.mol.molapi.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mol.molapi.exceptions.GenericIdException;
import br.com.mol.molapi.exceptions.GenericIdExceptionResponse;
import br.com.mol.molapi.exceptions.LoginInvalidException;
import br.com.mol.molapi.exceptions.UserEmailException;
import br.com.mol.molapi.exceptions.UserEmailExceptionResponse;
import br.com.mol.molapi.utils.ErrorDetails;
import net.sf.jasperreports.engine.JRException;

@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		Map<String, String> errorsByField = result.getFieldErrors().stream()
				.collect(Collectors.toMap(FieldError::getField, ObjectError::getCode));
		ErrorDetails errorDetails = new ErrorDetails(new Date(), errorsByField, result.toString());
		return new ResponseEntity<>(errorDetails, status);
	}

	@ExceptionHandler(JRException.class)
	public final ResponseEntity<ErrorDetails> handleAnyJasperException(JRException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), Arrays.asList(ex.getMessage()),
				request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), Arrays.asList(ex.getMessage() + " not found!"),
				request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(LoginInvalidException.class)
	public ResponseEntity<ErrorDetails> loginException(LoginInvalidException ex, WebRequest request) {
		return new ResponseEntity<>(
				new ErrorDetails(new Date(), Arrays.asList(ex.getMessage()), request.getDescription(true)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserEmailException.class)
	public ResponseEntity<UserEmailExceptionResponse> userEmailException(UserEmailException ex, WebRequest request) {
		return new ResponseEntity<>(new UserEmailExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GenericIdException.class)
	public ResponseEntity<GenericIdExceptionResponse> genericIdException(GenericIdException ex, WebRequest request) {
		return new ResponseEntity<>(new GenericIdExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
