package br.com.mol.molapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.mol.molapi.entity.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class User {

	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	@Column(length = 30, unique = true)
	private String email;

	@NotNull
	@Column(length = 50)
	private String name;

	@NotNull
	@Column(length = 36)
	private String password;

	@Column(length = 1)
	private boolean resetPassword;

	@Column(length = 1)
	private boolean confirmed;

	@Column(length = 1)
	private boolean active;

	@NotNull
	@Column(length = 14, unique = true)
	private String cpf;

	@Column(length = 50)
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	@NotNull
	private Gender gender;

	@Temporal(value = TemporalType.DATE)
	@NotNull
	private Date birthDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedAt;

	User(User user) {
		this.name = user.getName();
		this.cpf = user.getCpf();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.resetPassword = user.isResetPassword();
		this.confirmed = user.isConfirmed();
		this.birthDate = user.getBirthDate();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
		this.gender = user.getGender();
	}

}
