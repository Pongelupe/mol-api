package br.com.mol.molapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserEmailExceptionResponse {
	private String email;
}
