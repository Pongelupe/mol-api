package br.com.mol.molapi.dtos.user;

import java.util.Date;

import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.entity.enums.Gender;
import br.com.mol.molapi.utils.DTOConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private String id;
	private String name;
	private String cpf;
	private String phone;
	private Gender gender;
	private String email;
	private Date birthDate;
	
	public UserDTO(User user) {
		DTOConverter.mapPropertiesTo(user, this);
	}
}
