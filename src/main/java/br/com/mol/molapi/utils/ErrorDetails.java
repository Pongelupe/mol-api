package br.com.mol.molapi.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDetails {
	private Date timestamp;
	private Map<String, String> messagesByField;
	private List<String> messages;
	private String details;

	public ErrorDetails(Date timestamp, Map<String, String> messagesByField, String details) {
		super();
		this.timestamp = timestamp;
		this.messagesByField = messagesByField;
		this.details = details;
	}

	public ErrorDetails(Date timestamp, List<String> messages, String details) {
		super();
		this.timestamp = timestamp;
		this.messages = messages;
		this.details = details;
	}

}
