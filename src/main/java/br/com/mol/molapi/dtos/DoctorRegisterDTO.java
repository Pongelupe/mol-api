package br.com.mol.molapi.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DoctorRegisterDTO extends UserRegisterDTO {
	
	@NotBlank(message = "Doctor field is required.")
	@Size(max = 50, message = "Doctor field cant have more than 50 characters.")
	private String field;
	
	@NotBlank(message = "Doctor rg is required.")
	@Size(max = 20, message = "Doctor rg cant have more than 50 characters.")
	private String rg;
	
	@NotBlank(message = "Doctor state is required.")
	private String state;
	
	@NotBlank(message = "Doctor crm is required.")
	@Size(max = 30, message = "Doctor crm cant have more than 30 characters.")
	private String crm;
}
