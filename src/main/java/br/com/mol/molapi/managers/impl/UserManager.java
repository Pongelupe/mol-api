package br.com.mol.molapi.managers.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.managers.IUserManager;

@Component
public class UserManager implements IUserManager {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User prepareNewUser(UserRegisterDTO userRegisterDTO) {
		User user = new User();
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setName(userRegisterDTO.getName());
		user.setEmail(userRegisterDTO.getEmail());
		user.setBirthDate(userRegisterDTO.getBirthDate());
		user.setGender(userRegisterDTO.getGender());
		user.setCpf(userRegisterDTO.getCpf());
		user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword().orElseGet(user::getName)));
		boolean isActive = userRegisterDTO.getPassword().isPresent();
		user.setActive(isActive);
		user.setResetPassword(!isActive);
		user.setConfirmed(false);

		return user;
	}

}
