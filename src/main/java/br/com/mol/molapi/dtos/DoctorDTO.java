package br.com.mol.molapi.dtos;

import br.com.mol.molapi.entity.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorDTO extends UserDTO {
	
	private State state;
	private String rg;
	private String field;
	private String crm;
}
