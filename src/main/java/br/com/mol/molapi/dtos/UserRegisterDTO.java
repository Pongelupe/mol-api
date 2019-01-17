package br.com.mol.molapi.dtos;

import java.util.Date;
import java.util.Optional;

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
	private Optional<String> password = Optional.empty();

	void setPassword(String password) {
		this.password = Optional.ofNullable(password);
	}
}
