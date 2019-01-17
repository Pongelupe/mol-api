package br.com.mol.molapi.managers;

import br.com.mol.molapi.dtos.user.UserRegisterDTO;
import br.com.mol.molapi.entity.User;

public interface IUserManager {
	
	User prepareNewUser(User user, UserRegisterDTO userRegisterDTO);

}
