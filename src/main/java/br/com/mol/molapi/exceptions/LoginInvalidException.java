package br.com.mol.molapi.exceptions;

public class LoginInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3294338994068515925L;
	
	public LoginInvalidException() {
		super("O login está incorreto");
	}

}
