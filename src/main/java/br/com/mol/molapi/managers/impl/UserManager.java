package br.com.mol.molapi.managers.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.managers.IUserManager;
import br.com.mol.molapi.utils.DTOConverter;

@Component
public class UserManager implements IUserManager {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User prepareNewUser(User user, UserRegisterDTO userRegisterDTO) {
		DTOConverter.mapPropertiesTo(userRegisterDTO, user);
		
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setPassword(userRegisterDTO.getPassword().orElseGet(() -> UUID.randomUUID().toString()));
		user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword().orElseGet(user::getName)));
		
		boolean isActive = userRegisterDTO.getPassword().isPresent();
		user.setActive(isActive);
		user.setResetPassword(!isActive);
		user.setConfirmed(false);

		return user;
	}

}
