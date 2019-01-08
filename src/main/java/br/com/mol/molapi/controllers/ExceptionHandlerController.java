package br.com.mol.molapi.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mol.molapi.utils.ErrorDetails;
import net.sf.jasperreports.engine.JRException;

@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<String> errorCodes = result.getAllErrors().stream().map(ObjectError::getCode).collect(Collectors.toList());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), errorCodes, result.toString());
		return new ResponseEntity<>(errorDetails, status);
	}

	@ExceptionHandler(JRException.class)
	public final ResponseEntity<ErrorDetails> handleAnyJasperException(JRException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), Arrays.asList(ex.getMessage()),
				request.getDescription(true));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
