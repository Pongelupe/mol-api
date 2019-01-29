package br.com.mol.molapi.dtos.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorDTO extends UserDTO {
	
	private String rg;
	private String field;
	private String crm;
}
