package br.com.mol.molapi.dtos.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DoctorRegisterDTO extends UserRegisterDTO {

	private String field;
	private String rg;
	private String crm;
	private String digitalSignatureBase64;
	private String phone;
}
