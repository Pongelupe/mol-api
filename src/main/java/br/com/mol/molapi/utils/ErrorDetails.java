package br.com.mol.molapi.utils;

import java.util.Date;
import java.util.List;

import lombok.Value;

@Value
public class ErrorDetails {
	private Date timestamp;
	private List<String> messages;
	private String details;

}
