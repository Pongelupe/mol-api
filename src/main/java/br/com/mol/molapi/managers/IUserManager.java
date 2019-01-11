package br.com.mol.molapi.managers;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.entity.User;

public interface IUserManager {
	
	User prepareNewUser(UserRegisterDTO userRegisterDTO);

}
