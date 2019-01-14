package br.com.mol.molapi.dtos;

import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.entity.enums.Gender;
import br.com.mol.molapi.utils.DTOConverter;
import lombok.Data;

@Data
public class UserDTO {
	private String name;
	private String cpf;
	private Gender gender;
	private String email;

	public UserDTO(User user) {
		DTOConverter.mapPropertiesTo(user, this);
	}
}
