package br.com.mol.molapi.dtos;

import lombok.Data;

@Data
public class DoctorDTO {
	private String uuid;
	private String email;
	private String name;
	private String field;
	private String state;
}
