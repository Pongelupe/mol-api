package br.com.mol.molapi.managers.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.mol.molapi.dtos.user.UserRegisterDTO;
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
		Optional<String> optionalPassword = Optional.ofNullable(userRegisterDTO.getPassword());

		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setPassword(passwordEncoder.encode(optionalPassword.orElseGet(user::getName)));

		boolean isActive = optionalPassword.isPresent();
		user.setActive(isActive);
		user.setResetPassword(!isActive);
		user.setConfirmed(false);

		return user;
	}

}
