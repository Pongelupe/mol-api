package br.com.mol.molapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.mol.molapi.entity.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Pacient extends User {

	@Temporal(value = TemporalType.DATE)
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 8)
	private Gender gender;
}
