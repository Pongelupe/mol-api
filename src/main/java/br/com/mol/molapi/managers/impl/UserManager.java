package br.com.mol.molapi.managers.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.mol.molapi.dtos.UserRegisterDTO;
import br.com.mol.molapi.entity.User;
import br.com.mol.molapi.managers.IUserManager;

@Component
public class UserManager implements IUserManager {
	
	@Override
	public User prepareNewUser(User user, UserRegisterDTO userRegisterDTO) {
		BeanUtils.copyProperties(userRegisterDTO, user);
		
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setPassword(userRegisterDTO.getPassword().orElseGet(() -> UUID.randomUUID().toString()));
		
		boolean isActive = userRegisterDTO.getPassword().isPresent();
		user.setActive(isActive);
		user.setResetPassword(!isActive);
		user.setConfirmed(false);

		return user;
	}

}
