package br.com.mol.molapi.dtos;

import br.com.mol.molapi.entity.enums.State;
import lombok.Data;

@Data
public class DoctorDTO {
	private String id;
	private String email;
	private String name;
	private State state;
	private String field;
}
