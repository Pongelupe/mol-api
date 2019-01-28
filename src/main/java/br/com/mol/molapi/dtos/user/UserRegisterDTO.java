package br.com.mol.molapi.dtos.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mol.molapi.entity.enums.Gender;
import lombok.Data;

@Data
public class UserRegisterDTO {

	private String email;
	private String name;
	private String cpf;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private Gender gender;
	private String phone;
	private String password;

}
