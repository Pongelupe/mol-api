package br.com.mol.molapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.mol.molapi.entity.enums.Gender;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class User {

	@Id
	@Column(length = 36)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
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
	private boolean confirmed;

	@Column(length = 1)
	private boolean active;

	@NotNull
	@Column(length = 13, unique = true)
	private String cpf;

	@Column(length = 50)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	private Gender gender;

	@Temporal(value = TemporalType.DATE)
	private Date birthDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedAt;

}
