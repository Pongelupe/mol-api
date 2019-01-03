package br.com.mol.molapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class User {

	@Id
	@Column(length = 36)
	private String id;

	@NotNull
	@Column(length = 30, unique = true)
	private String email;

	@NotNull
	@Column(length = 15)
	private String password;

	@Column(length = 1)
	private boolean resetPassword;

	@Column(length = 1)
	private boolean registerConfirmed;

	@Column(length = 1)
	private boolean active;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

}
